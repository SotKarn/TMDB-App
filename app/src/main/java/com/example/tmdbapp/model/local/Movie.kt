package com.example.tmdbapp.model.local

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Float,
    val poster_path: String
)
