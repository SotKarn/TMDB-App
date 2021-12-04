package com.example.tmdbapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbapp.model.cache.MovieRemoteKey
import com.example.tmdbapp.model.web.MovieEntity

@Database(entities = [MovieEntity::class, MovieRemoteKey::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun getDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

    companion object {
        const val DATABASE_NAME: String = "movies_db"
    }
}