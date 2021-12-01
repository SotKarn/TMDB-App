package com.example.tmdbapp.model.web

import com.example.tmdbapp.model.local.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WebMoviesResponse(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<WebMovieEntity>
)
