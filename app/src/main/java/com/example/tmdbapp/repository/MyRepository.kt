package com.example.tmdbapp.repository

import androidx.paging.*
import com.example.tmdbapp.model.MoviesResponse
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.model.MovieReviews
import com.example.tmdbapp.paging.PopularMoviesRemoteMediator
import com.example.tmdbapp.paging.SearchPagingSource
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
            remoteMediator = PopularMoviesRemoteMediator(movieDatabase, webService),
            pagingSourceFactory = {movieDatabase.getDao().getAllMoviesPages()}
            ).flow
    }
    @ExperimentalPagingApi
    fun searchMovie(query: String?): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {SearchPagingSource(webService, query)}
        ).flow
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