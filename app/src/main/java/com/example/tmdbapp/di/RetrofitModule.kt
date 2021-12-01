package com.example.tmdbapp.di

import com.example.tmdbapp.retrofit.IWebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun providesWebService(retrofit: Retrofit.Builder): IWebService {
        return retrofit.build().create(IWebService::class.java)
    }


}