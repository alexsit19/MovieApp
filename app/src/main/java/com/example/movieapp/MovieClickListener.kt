package com.example.movieapp

import com.example.movieapp.data.Movie

interface MovieClickListener {
    fun clickOnItem(movie: Movie)
}