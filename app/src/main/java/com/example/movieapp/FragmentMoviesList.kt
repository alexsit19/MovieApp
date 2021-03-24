package com.example.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.Movie
import com.example.movieapp.data.loadMovies
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {

    private var moviesAdapter: MoviesAdapter? = null
    private val exceptionHandler = CoroutineExceptionHandler {
        coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private var scope = CoroutineScope(
        Job() +
                Dispatchers.IO +
                exceptionHandler
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter()
        val rvMovies = view.findViewById<View>(R.id.rvMovies) as RecyclerView
        rvMovies.adapter = moviesAdapter
        rvMovies.addItemDecoration(SimpleDividerItemDecoration(25))
        rvMovies.isNestedScrollingEnabled = false
        rvMovies.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    override fun onStart() {
        super.onStart()
        scope.launch {
            val moviesList = loadMovies(requireContext())
            moviesAdapter?.updateMovies(moviesList)
        }

    }
}