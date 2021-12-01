package com.example.tmdbapp.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbapp.room.MovieDatabase
import com.example.tmdbapp.room.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule
{
    @Provides
    @Singleton
    fun providesMovieDatabase(@ApplicationContext context: Context): MovieDatabase{
        return Room.databaseBuilder(context, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(movieDatabase: MovieDatabase): MoviesDao{
        return movieDatabase.getDao()
    }
}