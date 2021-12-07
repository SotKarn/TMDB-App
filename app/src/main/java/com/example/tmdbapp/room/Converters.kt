package com.example.tmdbapp.room

import androidx.room.TypeConverter
import com.example.tmdbapp.model.MovieInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromGenre(list: List<MovieInfo.Genre>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieInfo.Genre>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toGenre(genre: String): List<MovieInfo.Genre> {
        val gson = Gson()
        val type = object :
            TypeToken<List<MovieInfo.Genre>>() {}.type
        return gson.fromJson(genre, type)
    }


    @TypeConverter
    fun fromCredits(credits: MovieInfo.Credits): String = Gson().toJson(credits)


    @TypeConverter
    fun toCredits(credits: String): MovieInfo.Credits =
        Gson().fromJson(credits, MovieInfo.Credits::class.java)
}