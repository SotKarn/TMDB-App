package com.example.tmdbapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieReviewEntity(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("content")
    @Expose
    val content: String,

    @SerializedName("created_at")
    @Expose
    val created_at: String,

    @SerializedName("url")
    @Expose
    val url: String,

)