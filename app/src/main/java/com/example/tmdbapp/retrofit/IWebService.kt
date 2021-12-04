package com.example.tmdbapp.retrofit

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.model.web.MoviesResponse
import com.example.tmdbapp.model.web.MovieEntity
import com.example.tmdbapp.model.web.MovieReviews
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWebService
{
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("page") page: Int) : MoviesResponse

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("query") query:String): MoviesResponse

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getMovieInfo(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY): MovieEntity

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY): MovieReviews

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY ): MoviesResponse

}