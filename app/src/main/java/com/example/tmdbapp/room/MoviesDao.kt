package com.example.tmdbapp.room

import androidx.room.*
import com.example.tmdbapp.model.cache.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: MovieEntity): Long

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>?

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}