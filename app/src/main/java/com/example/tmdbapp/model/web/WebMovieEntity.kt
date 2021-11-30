package com.example.tmdbapp.model.web

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WebMovieEntity(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("release_data")
    @Expose
    val release_data: String,

    @SerializedName("vote_average")
    @Expose
    val vote_average: Float,

    @SerializedName("poster_path")
    @Expose
    val poster_path: String
)