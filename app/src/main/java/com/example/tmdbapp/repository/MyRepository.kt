package com.example.tmdbapp.repository

import androidx.paging.*
import com.example.tmdbapp.model.web.WebMoviesResponse
import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.model.web.WebMovieReviews
import com.example.tmdbapp.paging.MoviePagingSource
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MoviesDao
import com.example.tmdbapp.utils.RoomEntityMapper
import com.example.tmdbapp.utils.WebEntityMapper
import kotlinx.coroutines.flow.Flow


class MyRepository(
    private val webService: IWebService,
    private val dao: MoviesDao,
    private val webIEntityMapper: WebEntityMapper,
    private val roomEntityMapper: RoomEntityMapper
) {

     fun getPopularMovies(): Flow<PagingData<WebMovieEntity>> =
          Pager(
             config = PagingConfig(
                 pageSize = 20,
                 maxSize = 100,
                 enablePlaceholders = false
             ),
             pagingSourceFactory = { MoviePagingSource(webService) }
         ).flow

    suspend fun searchMovie(query: String): WebMoviesResponse {
        return webService.searchMovie(query = query)
    }

    suspend fun getMovieInfo(id: Int): WebMovieEntity {
        return webService.getMovieInfo(movieId = id)
    }

    suspend fun getMovieReviews(id: Int): WebMovieReviews {
        return webService.getMovieReviews(movieId = id)
    }

    suspend fun getSimilarMovies(id: Int): WebMoviesResponse {
        return webService.getSimilarMovies(movieId = id)
    }
}