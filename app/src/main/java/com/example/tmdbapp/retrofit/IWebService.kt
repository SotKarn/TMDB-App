package com.example.tmdbapp.retrofit

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.model.web.WebMoviesResponse
import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.model.web.WebMovieReviews
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWebService
{
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("page") page: Int) : WebMoviesResponse

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("query") query:String): WebMoviesResponse

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getMovieInfo(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY): WebMovieEntity

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY): WebMovieReviews

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY ): WebMoviesResponse

}