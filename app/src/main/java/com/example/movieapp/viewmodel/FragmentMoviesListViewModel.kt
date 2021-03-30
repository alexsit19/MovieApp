package com.example.movieapp.viewmodel

import android.content.Context
import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.example.movieapp.MoviesApplication
import com.example.movieapp.data.Movie
import com.example.movieapp.data.loadMovies
import com.example.movieapp.ui.FragmentMoviesList
import com.example.movieapp.ui.MainActivity
import kotlinx.coroutines.*

class FragmentMoviesListViewModel: ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Movie>>()

    val liveData: LiveData<List<Movie>> = mutableLiveData

    fun loadData() {
        viewModelScope.launch {
            mutableLiveData.value = loadMovies(MoviesApplication.getInstance())
        }
    }

    fun getData(): LiveData<List<Movie>> {
        loadData()
        return liveData
    }

//    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("CoroutineExceptionHandler got $exception in $coroutineContext")
//    }

//    private var scope = CoroutineScope(
//            Job() +
//                    Dispatchers.IO +
//                    exceptionHandler
//    )
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    suspend fun loadMovies() {
//        scope.launch {
//            moviesList = loadMovies(MoviesApplication.context)
//            // moviesAdapter?.updateMovies(moviesList)
//        }
//    }
}