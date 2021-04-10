package com.example.movieapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.example.movieapp.MoviesApplication
import com.example.movieapp.data.Movie
//import com.example.movieapp.data.loadMovies
import com.example.movieapp.data.network.MovieApi
import com.example.movieapp.ui.FragmentMoviesList
import com.example.movieapp.ui.MainActivity
import kotlinx.coroutines.*

const val apiKey = "e65ad475d75413043d534a5746a8cbbf"

class FragmentMoviesListViewModel: ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Movie>>()

    val liveData: LiveData<List<Movie>> = mutableLiveData

    private fun loadData() {
        viewModelScope.launch {
            //mutableLiveData.value = loadMovies(MoviesApplication.getInstance())
            try {
                mutableLiveData.value = MovieApi.retrofitService.getMovies(apiKey).movies
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