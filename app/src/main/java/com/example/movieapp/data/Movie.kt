package com.example.movieapp.data

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: String,
    val genres: String,
)