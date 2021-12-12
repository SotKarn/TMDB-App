package com.example.tmdbapp.di

import com.example.tmdbapp.retrofit.IWebService
import com.example.tmdbapp.retrofit.IWebService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{


    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun providesWebService(retrofit: Retrofit.Builder): IWebService {
        return retrofit.build().create(IWebService::class.java)
    }


}