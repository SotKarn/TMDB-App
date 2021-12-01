package com.example.tmdbapp.repository

import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.model.web.WebMoviesResponse
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MoviesDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyRepositoryTest{
    @Mock
    lateinit var webService: IWebService

    @Mock
    lateinit var dao: MoviesDao

    private lateinit var repo: MyRepository

    @Before
    fun setUp()
    {
        MockitoAnnotations.openMocks(this)
        repo = MyRepository(webService,dao)
    }

    private val fakeResponse: WebMoviesResponse = WebMoviesResponse(1, listOf(
        WebMovieEntity(1, "test title 1", "test overview 1", "1/1/1", 5.6F,"test 1" ),
        WebMovieEntity(2, "test title 2", "test overview 2", "2/1/1", 7.3F,"test 2" )
    ))

    @Test
    fun `check webService invoke when getPopularMovies api`() {
        runBlocking {
            Mockito.`when`(webService.getPopularMovies()).thenReturn(fakeResponse)
            repo.getPopularMovies()
            Mockito.verify(webService, Mockito.times(1)).getPopularMovies()
        }
    }

    @Test
    fun `check size of returned movies from get popular movies api`() {
        runBlocking {
            Mockito.`when`(webService.getPopularMovies()).thenReturn(fakeResponse)
            val response = repo.getPopularMovies()
            assertEquals(2, response.results.size)
        }
    }
}
