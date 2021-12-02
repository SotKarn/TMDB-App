package com.example.tmdbapp.repository


import com.example.tmdbapp.model.cache.CachedMovie
import com.example.tmdbapp.model.cache.CachedMoviePage
import com.example.tmdbapp.model.local.Movie
import com.example.tmdbapp.model.web.WebMoviesResponse
import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.model.web.WebMovieReviews
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MoviesDao
import com.example.tmdbapp.utils.RoomEntityMapper
import com.example.tmdbapp.utils.WebEntityMapper
import kotlinx.coroutines.runBlocking

class MyRepository(
    private val webService: IWebService,
    private val dao: MoviesDao,
    private val webIEntityMapper: WebEntityMapper,
    private val roomEntityMapper: RoomEntityMapper
) {
    private var page: Int = 1

    suspend fun getPopularMovies(): List<CachedMovie>? {
        //Get all cached movies
        val roomPageList: List<CachedMoviePage> = dao.getAllMoviesPages()

        val roomMovies: MutableList<CachedMovie> = mutableListOf()

        when {
            //If cache is not empty
            roomPageList.isNotEmpty() -> {

                //Check if the page is stored in local storage
                val storedPage: CachedMoviePage? = roomPageList.find { it.page == page}

                // If it's found return the movies from that particular page. Otherwise fetch from API
                if (storedPage != null)
                {
                    roomMovies += storedPage.movies
                }
                else getMoviePageFromApi()?.let { roomMovies += it}
            }
            else -> getMoviePageFromApi()?.let { roomMovies += it}

        }
        page ++
        return roomMovies
    }

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


    private suspend fun getMoviePageFromApi(): List<CachedMovie>? {
        // Get popular movies from api
        val response: WebMoviesResponse? = webService.getPopularMovies(page = page)

        return response?.let {
            //Convert to local entities
            val localEntityList: List<Movie> = webIEntityMapper.mapWebListToLocal(it.results)

            //Convert to room entities
            val roomEntityPage = roomEntityMapper.mapWebResponseToCached(it, localEntityList)

            //Insert all elements to local storage
            dao.insertMoviePage(roomEntityPage)
            roomEntityPage.movies
        }
    }

}