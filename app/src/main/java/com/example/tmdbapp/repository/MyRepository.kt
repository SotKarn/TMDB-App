package com.example.tmdbapp.repository


import com.example.tmdbapp.model.web.WebMoviesResponse
import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.model.web.WebMovieReviews
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MoviesDao

class MyRepository(
    private val webService: IWebService,
    private val dao: MoviesDao
)
{

    suspend fun getPopularMovies(): WebMoviesResponse
    {
        return webService.getPopularMovies()
    }

    suspend fun searchMovie(query: String): WebMoviesResponse
    {
        return webService.searchMovie(query = query)
    }

    suspend fun getMovieInfo(id: Int): WebMovieEntity
    {
        return webService.getMovieInfo(movieId = id)
    }

    suspend fun getMovieReviews(id: Int): WebMovieReviews
    {
        return webService.getMovieReviews(movieId = id)
    }

    suspend fun getSimilarMovies(id: Int): WebMoviesResponse
    {
        return webService.getSimilarMovies(movieId = id)
    }
}