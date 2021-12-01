package com.example.tmdbapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbapp.model.cache.CachedMovie

@Database(entities = [CachedMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun getDao(): MoviesDao

    companion object {
        val DATABASE_NAME: String = "movies_db"
    }
}