package com.example.tmdbapp.model.local


data class MovieReview(
    val id: Int,
    val author: String,
    val content: String,
    val created_at: String,
    val url: String
)
