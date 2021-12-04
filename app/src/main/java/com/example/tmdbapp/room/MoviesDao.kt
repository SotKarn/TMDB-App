package com.example.tmdbapp.room


import androidx.paging.PagingSource
import androidx.room.*

import com.example.tmdbapp.model.web.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovies(list: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getAllMoviesPages(): PagingSource<Int,MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}