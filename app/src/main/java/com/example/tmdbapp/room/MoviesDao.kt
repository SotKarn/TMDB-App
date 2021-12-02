package com.example.tmdbapp.room

import androidx.room.*
import com.example.tmdbapp.model.cache.CachedMovie
import com.example.tmdbapp.model.cache.CachedMoviePage

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePage(cachedMoviePageEntity: CachedMoviePage): Long

    @Query("SELECT * FROM movie_pages")
    suspend fun getAllMoviesPages(): List<CachedMoviePage>

    @Query("DELETE FROM movie_pages")
    suspend fun deleteAll()
}