package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), MovieClickListener {
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

    override fun clickOnItem(movieId: Int) {
        Log.d("DEBUG", "clickOnItem $movieId")
    }
}