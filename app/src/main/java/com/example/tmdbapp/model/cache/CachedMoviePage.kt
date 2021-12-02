package com.example.tmdbapp.model.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_pages")
data class CachedMoviePage (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "page")
    val page:Int,

    @ColumnInfo(name = "movies")
    val movies: List<CachedMovie>
)