package com.example.movietime.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movietime.data.local.MovieDatabase
import com.example.movietime.data.local.MovieEntity
import com.example.movietime.data.mapper.toMovieEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDatabase: MovieDatabase,
    private val movieApi: MovieApi,
): RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    }else{
                        (lastItem.autoId!! / state.config.pageSize) + 1
                    }
                }
            }

            delay(1000L)
            val movies = movieApi.getMovies(
                page = loadKey,
                limit = state.config.pageSize
            )

            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    movieDatabase.dao.clearAll()
                }
                val movieEntity = movies.data?.movies!!.map { it.toMovieEntity() }
                movieDatabase.dao.upsertAll(movieEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = movies.data?.movies!!.isEmpty()
            )
        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}