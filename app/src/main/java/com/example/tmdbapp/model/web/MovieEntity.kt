package com.example.tmdbapp.model.web

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val customID: Int = 0,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Float,

    @ColumnInfo(name = "poster_path")
    val poster_path: String
): Parcelable