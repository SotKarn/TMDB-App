package com.example.tmdbapp.model.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_data")
    val release_data: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Float,

    @ColumnInfo(name = "poster_path")
    val poster_path: String
)
