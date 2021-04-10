package com.example.movieapp.data

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "poster_path")
    val poster: String,
    @Json(name = "vote_average")
    val ratings: Float,

    val adult: Boolean,
    //val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)