package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.business.MovieListInteractor
import com.example.movieapp.data.NetworkGenreRepository
import com.example.movieapp.data.NetworkMovieListRepository
import com.example.movieapp.data.converter.GenreMapper
import com.example.movieapp.data.converter.MovieMapper

class MoviesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            FragmentMoviesListViewModel::class.java     -> FragmentMoviesListViewModel(
                    MovieListInteractor(
                            NetworkMovieListRepository(MovieMapper()),
                            NetworkGenreRepository(GenreMapper()))
            )
            FragmentMovieDetailsViewModel::class.java   -> FragmentMovieDetailsViewModel()
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

        return viewModel
    }
}