package com.example.tmdbapp.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbapp.model.cache.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun getDao(): Dao

    companion object {
        val DATABASE_NAME: String = "movies_db"
    }
}