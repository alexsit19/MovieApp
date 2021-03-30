package com.example.movieapp

import android.app.Application
import android.content.Context


class MoviesApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MoviesApplication? = null

        fun getInstance() : MoviesApplication {
            return instance!!
        }
    }

//    override fun onCreate() {
//        super.onCreate()
//        context = applicationContext
//    }
//        companion object {
//            lateinit var context: Context
//        }
    }


