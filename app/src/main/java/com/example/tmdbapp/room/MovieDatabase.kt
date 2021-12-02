package com.example.tmdbapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.tmdbapp.model.cache.CachedMoviePage

@Database(entities = [CachedMoviePage::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun getDao(): MoviesDao

    companion object {
        const val DATABASE_NAME: String = "movies_db"
    }
}