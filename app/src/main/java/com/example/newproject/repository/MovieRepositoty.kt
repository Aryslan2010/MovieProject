package com.example.newproject.repository

import com.example.newproject.data.Movie
import com.example.newproject.data.MovieResponseImages
import com.example.newproject.db.MovieEntity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRepositoty {
    suspend fun getMovieDetails(movieId:Long): Movie?
    suspend fun getSDASD(movieId:Long): Movie?

    suspend fun getMovieImage(movieId:Long): MovieResponseImages?

    interface MovieApi {
        @GET("movie/{movieId}")
        fun getMovieDetails(@Path("movieId") movieId: Long, @Query("api_key")api_key: String): Call<Movie>

        @GET("movie/{movieId}/images")
        fun getMovieImages(
            @Path("movieId") movieId: Long?,
            @Query("api_key") apiKey: String
        ): Call<MovieResponseImages>

        companion object{
            val INSTANCE = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
                .create(MovieApi::class.java)
        }
    }
}