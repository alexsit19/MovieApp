package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.place_for_fragment, FragmentMoviesList())
                commit()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

//    override fun clickOnItem(movie: Movie) {
//        Log.d("DEBUG", "clickOnItem ${movie.id}")
//
//        supportFragmentManager.beginTransaction()
//                .apply {
//                    add(R.id.place_for_fragment, FragmentMovieDetails.newInstance(movie.id))
//                    addToBackStack(null)
//                    commit()
//                }
//    }
}