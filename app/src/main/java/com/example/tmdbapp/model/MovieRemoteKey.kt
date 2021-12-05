package com.example.tmdbapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val movieId: Int,
    val prev: Int?,
    val next: Int?
)
