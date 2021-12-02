package com.example.tmdbapp.room

import androidx.room.TypeConverter
import com.example.tmdbapp.model.cache.CachedMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromCachedMovieList(countryLang: List<CachedMovie?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<CachedMovie?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCachedMovieList(countryLangString: String?): List<CachedMovie>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<CachedMovie?>?>() {}.type
        return gson.fromJson<List<CachedMovie>>(countryLangString, type)
    }
}