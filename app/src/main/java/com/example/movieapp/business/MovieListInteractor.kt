package com.example.movieapp.business

import com.example.movieapp.data.GenreRepository
import com.example.movieapp.data.GenresSource
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieListInteractor (
    private val movieListRepository: MovieListRepository,
    private val genresRepository: GenreRepository
) {
    suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) {
        if (GenresSource.genres.isEmpty()) {
            genresRepository.loadGenres()
        }
        movieListRepository.loadMovies()
    }
}