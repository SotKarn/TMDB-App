package com.example.tmdbapp.model.cache

data class CachedMovieReview(
    val id: Int,
    val author: String,
    val content: String,
    val created_at: String,
    val url: String
)