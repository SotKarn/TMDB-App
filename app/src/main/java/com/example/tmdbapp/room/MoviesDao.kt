package com.example.tmdbapp.room

import androidx.room.*
import com.example.tmdbapp.model.cache.CachedMovie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: CachedMovie): Long

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<CachedMovie>?

    @Delete
    suspend fun deleteMovie(movieEntity: CachedMovie)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}