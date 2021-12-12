package com.example.tmdbapp.repository

import androidx.paging.*
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.model.MovieInfo
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

    fun getMovieInfo(id: Int): Flow<MovieInfo> = flow {

        val cachedMovieInfo: MovieInfo? = movieDatabase.getMoviesInfoDao().getMovieInfo(id)

        cachedMovieInfo?.let {
            emit(it)
        } ?: kotlin.run{
            val apiMovieInfo = webService.getMovieInfo(movieId = id)
            movieDatabase.getMoviesInfoDao().insertMovieInfo(apiMovieInfo)
            emit(apiMovieInfo)
        }
    }

    @ExperimentalPagingApi
    suspend fun getSimilarMovies(id: Int): Flow<List<MovieEntity>> = flow {
        val results = webService.getSimilarMovies(movieId = id).results
        emit(results)
    }
}