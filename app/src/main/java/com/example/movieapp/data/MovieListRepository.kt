package com.example.movieapp.data

import android.net.Network
import com.example.movieapp.data.converter.MovieMapper
import com.example.movieapp.data.network.MovieApi
import com.example.movieapp.data.network.MovieApiService

interface MovieListRepository {
    suspend fun loadMovies(): List<Movie>
}

class NetworkMovieListRepository (private val mapper: MovieMapper): MovieListRepository {
    override suspend fun loadMovies(): List<Movie> {
        val movieResponse = MovieApi.retrofitService.getMovies(MovieApiService.API_KEY)
        return movieResponse.movies.map {
            mapper.toMovie(it, GenresSource.genres)
        }
    }
}

