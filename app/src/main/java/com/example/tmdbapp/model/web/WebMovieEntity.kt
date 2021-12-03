package com.example.tmdbapp.model.web

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebMovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Float,
    val poster_path: String,
): Parcelable