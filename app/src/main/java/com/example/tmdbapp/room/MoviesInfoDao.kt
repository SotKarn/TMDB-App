package com.example.tmdbapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbapp.model.MovieInfo

@Dao
interface MoviesInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieInfo(movieInfo: MovieInfo)

    @Query("SELECT * FROM movies_info WHERE id= :id")
    suspend fun getMovieInfo(id:Int): MovieInfo?

    @Query("DELETE FROM movies_info")
    suspend fun deleteAll()
}