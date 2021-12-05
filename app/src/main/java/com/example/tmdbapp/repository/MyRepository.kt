package com.example.tmdbapp.repository

import androidx.paging.*
import com.example.tmdbapp.model.web.MoviesResponse
import com.example.tmdbapp.model.web.MovieEntity
import com.example.tmdbapp.model.web.MovieReviews
import com.example.tmdbapp.paging.MyRemoteMediator
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MovieDatabase
import kotlinx.coroutines.flow.*

class MyRepository(
    private val webService: IWebService,
    private val movieDatabase: MovieDatabase
) {

    @ExperimentalPagingApi
    fun getPopularMovies(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            remoteMediator = MyRemoteMediator(movieDatabase, webService),
            pagingSourceFactory = {movieDatabase.getDao().getAllMoviesPages()}
            ).flow
    }
    suspend fun searchMovie(query: String): MoviesResponse {
        return webService.searchMovie(query = query)
    }

    suspend fun getMovieInfo(id: Int): MovieEntity {
        return webService.getMovieInfo(movieId = id)
    }

    suspend fun getMovieReviews(id: Int): MovieReviews {
        return webService.getMovieReviews(movieId = id)
    }

    suspend fun getSimilarMovies(id: Int): MoviesResponse {
        return webService.getSimilarMovies(movieId = id)
    }
}