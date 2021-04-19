package com.example.movieapp.data

import android.net.Network
import com.example.movieapp.data.GenresSource.genres
import com.example.movieapp.data.converter.GenreMapper
import com.example.movieapp.data.network.MovieApi
import com.example.movieapp.data.network.MovieApiService

interface GenreRepository {
    suspend fun loadGenres(): List<Genre>
}

class NetworkGenreRepository (private val mapper: GenreMapper): GenreRepository {
    override suspend fun loadGenres(): List<Genre> {
        val genres = MovieApi.retrofitService.getGenres(MovieApiService.API_KEY).genres.map { mapper.toGenre(it) }
        GenresSource.saveGenres(genres)
        return genres
    }
}
