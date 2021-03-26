package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.Movie
import com.example.movieapp.data.loadMovie
import kotlinx.coroutines.*

class FragmentMovieDetails : Fragment() {

    private var actorAdapter: ActorAdapter? = null
    private var exceptionHandler = CoroutineExceptionHandler{
        coroutineContext, exception ->
        Log.d("DEBUG", "CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private val scope = CoroutineScope(
            SupervisorJob() +
                    Dispatchers.Main +
                    exceptionHandler
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = requireArguments().getInt(MOVIE_ID)

        scope.launch {

            loadMovie(movieId, requireContext())?.let {
                movie -> updateMovieInformation(view, movie)
            }
        }

        actorAdapter = ActorAdapter()

        val rvActors = view.findViewById<View>(R.id.rvActor) as RecyclerView
        rvActors.adapter = actorAdapter
        rvActors.addItemDecoration((SimpleDividerItemDecoration(25)))
        rvActors.layoutManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)

    }

    fun updateMovieInformation(view: View, movie: Movie?){

        val age = view.findViewById<TextView>(R.id.age)

        age.setText(movie?.minimumAge.toString() + "+")

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
                .load(movie?.backdrop)
                .placeholder(R.drawable.base_line_movie)
                .into(imageView)

        val reviews = view.findViewById<TextView>(R.id.reviews)
        reviews.setText(movie?.numberOfRatings.toString())

        val overview = view.findViewById<TextView>(R.id.storyLineContent)
        overview.setText(movie?.overview)

        val title = view.findViewById<TextView>(R.id.title)
        title.setText(movie?.title)

        val tagLine = view.findViewById<TextView>(R.id.tagLine)
        tagLine.setText(movie?.genres)


        if (movie?.actors!!.isNotEmpty()) {
            actorAdapter?.updateActors(movie.actors)

        } else {
            view.findViewById<View>(R.id.Cast).visibility = View.GONE
        }
    }

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val args = Bundle()
            args.putInt(MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment

        }
    }
}