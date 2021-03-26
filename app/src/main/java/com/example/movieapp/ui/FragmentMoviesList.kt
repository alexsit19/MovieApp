package com.example.movieapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.ui.movie.MoviesAdapter
import com.example.movieapp.R
import com.example.movieapp.ui.movie.SimpleDividerItemDecoration
import com.example.movieapp.data.Movie
import com.example.movieapp.data.loadMovies
import com.example.movieapp.viewmodel.FragmentMoviesListViewModel
import com.example.movieapp.viewmodel.MoviesViewModelFactory
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {

    private var moviesAdapter: MoviesAdapter? = null
    private var listener: (Movie) -> Unit = { clickOnItem(it) }
    private lateinit var viewModel: FragmentMoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, MoviesViewModelFactory()).get(FragmentMoviesListViewModel::class.java)

        moviesAdapter = MoviesAdapter(listener)
        val rvMovies = view.findViewById<View>(R.id.rvMovies) as RecyclerView
        rvMovies.adapter = moviesAdapter
        rvMovies.addItemDecoration(SimpleDividerItemDecoration(25))
        rvMovies.isNestedScrollingEnabled = false
        rvMovies.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        //clickListener = activity as MovieClickListener

    }

    override fun onDetach() {
        super.onDetach()

    }

    private fun clickOnItem(movie: Movie) {
        val id = movie.id
        Log.d("DEBUG", "dddddddddddddddd $id")
        (activity as MainActivity).apply {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.place_for_fragment, FragmentMovieDetails.newInstance(movie.id))
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }
}