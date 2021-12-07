package com.example.tmdbapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmdbapp.model.MovieRemoteKey
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.model.MovieInfo

@Database(entities = [MovieEntity::class, MovieRemoteKey::class, MovieInfo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun getDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun getMoviesInfoDao(): MoviesInfoDao

    companion object {
        const val DATABASE_NAME: String = "movies_db"
    }
}