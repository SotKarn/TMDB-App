package com.example.tmdbapp.model.web

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieReviews(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<ReviewEntity>

)
