package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            FragmentMoviesListViewModel::class.java     -> FragmentMoviesListViewModel()
            FragmentMovieDetailsViewModel::class.java   -> FragmentMovieDetailsViewModel()
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

        return viewModel
    }
}