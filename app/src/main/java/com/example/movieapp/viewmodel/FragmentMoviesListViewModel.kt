package com.example.movieapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.example.movieapp.MoviesApplication
import com.example.movieapp.business.MovieListInteractor
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieListRepository
//import com.example.movieapp.data.loadMovies
import com.example.movieapp.data.network.MovieApi
import com.example.movieapp.ui.FragmentMoviesList
import com.example.movieapp.ui.MainActivity
import kotlinx.coroutines.*

class FragmentMoviesListViewModel(private val movieListInteractor: MovieListInteractor): ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Movie>>()

    val liveData: LiveData<List<Movie>> = mutableLiveData

    private fun loadData() {
        viewModelScope.launch {
            //mutableLiveData.value = loadMovies(MoviesApplication.getInstance())
            try {
                mutableLiveData.value = movieListInteractor.getMovies()
                Log.d("DEBUG work", mutableLiveData.value.toString())
            } catch (e: Exception) {
                Log.d("DEBUG exception", "${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Movie>> {
        loadData()
        return liveData
    }
}