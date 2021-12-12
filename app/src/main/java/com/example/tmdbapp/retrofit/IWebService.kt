package com.example.tmdbapp.retrofit

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.model.MovieInfo
import com.example.tmdbapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWebService
{

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("page") page: Int) : MoviesResponse

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey:String = BuildConfig.API_KEY, @Query("query") query:String?, @Query("page") page:Int?) : MoviesResponse

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getMovieInfo(@Path("movie_id") movieId: Int, @Query("api_key") apiKey:String = BuildConfig.API_KEY): MovieInfo

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey:String = BuildConfig.API_KEY,
    ): MoviesResponse


    companion object{
        const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}