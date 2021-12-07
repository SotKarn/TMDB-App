package com.example.tmdbapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies_info")
@Parcelize
data class MovieInfo(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "genres")
    val genres: List<Genre>,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Float,

    @ColumnInfo(name = "credits")
    val credits: Credits,

    @ColumnInfo(name = "poster_path")
    val poster_path: String
) : Parcelable {

    @Entity
    @Parcelize
    data class Genre(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id:Int,
        @ColumnInfo(name = "name")
        val name:String
    ): Parcelable

    @Entity
    @Parcelize
    data class Credits(
        val cast: List<Cast>
    ): Parcelable
    {
        @Entity
        @Parcelize
        data class Cast(
            val id:Int,
            val name:String,
            val profile_path: String,
            val character:String
        ): Parcelable
    }
}

