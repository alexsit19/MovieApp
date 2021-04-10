package com.example.movieapp.data

import com.example.movieapp.data.network.MovieNetworkModel
import com.squareup.moshi.Json

data class ApiResponse (
        val page: String,
        @Json (name="results")
        val movies: List<MovieNetworkModel>
        )