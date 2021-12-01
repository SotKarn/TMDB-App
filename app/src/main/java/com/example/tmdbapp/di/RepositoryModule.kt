package com.example.tmdbapp.di

import com.example.tmdbapp.repository.MyRepository
import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.room.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(webService: IWebService, dao: MoviesDao): MyRepository
    {
        return MyRepository(webService, dao)
    }
}