package com.example.movietime.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.movietime.data.local.MovieDatabase
import com.example.movietime.data.local.MovieEntity
import com.example.movietime.data.remote.MovieApi
import com.example.movietime.data.remote.MovieApi.Companion.BASE_URL
import com.example.movietime.data.remote.MovieRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDB(@ApplicationContext context: Context) : MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("x-rapidapi-host", "movie-database-api1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "288e5deecemshed2c22c525ab680p19d322jsn8c4fdb5bf3fc")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient) : MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMoviePager(movieDatabase: MovieDatabase, movieApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = MovieRemoteMediator(
                movieDatabase,
                movieApi
            ),
            pagingSourceFactory = {
               movieDatabase.dao.pagingSource()
            }
        )
    }

}