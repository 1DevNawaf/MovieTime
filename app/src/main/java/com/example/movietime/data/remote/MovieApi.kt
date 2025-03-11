package com.example.movietime.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("list_movies.json")
    suspend fun getMovies(
        @Query("limit") limit : Int,
        @Query("page") page : Int
    ) : List<MovieDto>

    companion object{
        const val BASE_URL = "https://movie-database-api1.p.rapidapi.com/"
    }
}