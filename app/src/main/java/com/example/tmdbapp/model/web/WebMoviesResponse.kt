package com.example.tmdbapp.model.web

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WebMoviesResponse(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<WebMovieEntity>,

    @SerializedName("total_pages")
    @Expose
    val total_pages: Int
)
