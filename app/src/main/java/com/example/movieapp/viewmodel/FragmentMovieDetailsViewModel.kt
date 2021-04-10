package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.MoviesApplication
import com.example.movieapp.data.Actor
import com.example.movieapp.data.Movie
//import com.example.movieapp.data.loadMovie
import kotlinx.coroutines.launch

class FragmentMovieDetailsViewModel: ViewModel() {

    private var movie: Movie? = null
    private val liveActorList = MutableLiveData<List<Actor>>()
    private val liveImageMovie = MutableLiveData<String>()
    private val liveMovieDescription = MutableLiveData<String>()
    private val liveNameMovie = MutableLiveData<String>()
    private val liveReview = MutableLiveData<String>()
    private val liveGenre = MutableLiveData<String>()
    private val liveMinimumAge = MutableLiveData<String>()
    private val liveRating = MutableLiveData<Float>()

    fun loadData(id: Int) {
        viewModelScope.launch {
            //movie = loadMovie(id, MoviesApplication.getInstance())
            bind()
        }
    }

    fun getLiveActorList(): MutableLiveData<List<Actor>> {
        return liveActorList
    }

    fun getLiveImageMovie(): MutableLiveData<String> {
        return liveImageMovie
    }

    fun getLiveMovieDescription(): MutableLiveData<String> {
        return liveMovieDescription
    }

    fun getLiveNameMovie(): MutableLiveData<String> {
        return liveNameMovie
    }

    fun getLiveReview(): MutableLiveData<String> {
        return liveReview
    }

    fun getLiveGenre(): MutableLiveData<String> {
        return liveGenre
    }

    fun getLiveMinimumAge(): MutableLiveData<String> {
        return liveMinimumAge
    }

    fun getLiveRating(): MutableLiveData<Float> {
        return liveRating
    }

    private fun bind() {
        liveActorList.value = movie?.actors
        //liveImageMovie.value = movie?.backdrop
        liveMovieDescription.value = movie?.overview
        liveNameMovie.value = movie?.title
        //liveReview.value = movie?.numberOfRatings.toString()
        liveGenre.value = movie?.genres.toString()
        //liveMinimumAge.value = movie?.minimumAge.toString()
        liveRating.value = movie?.ratings

    }

}