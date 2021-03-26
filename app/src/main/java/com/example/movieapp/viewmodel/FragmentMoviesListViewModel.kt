package com.example.movieapp.viewmodel

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.movieapp.MoviesApplication
import com.example.movieapp.data.loadMovies
import kotlinx.coroutines.*

class FragmentMoviesListViewModel: ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private var scope = CoroutineScope(
            Job() +
                    Dispatchers.IO +
                    exceptionHandler
    )

    suspend fun loadMovies() {
        scope.launch {
            val moviesList = loadMovies(MoviesApplication.context)
            // moviesAdapter?.updateMovies(moviesList)
        }
    }
}