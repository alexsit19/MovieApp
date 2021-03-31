package com.example.movieapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.ui.actor.ActorAdapter
import com.example.movieapp.R
import com.example.movieapp.data.Actor
import com.example.movieapp.ui.movie.SimpleDividerItemDecoration
import com.example.movieapp.viewmodel.FragmentMovieDetailsViewModel
import com.example.movieapp.viewmodel.MoviesViewModelFactory

class FragmentMovieDetails : Fragment() {

    private var actorAdapter: ActorAdapter? = null
    private lateinit var viewModel: FragmentMovieDetailsViewModel
    private lateinit var liveActorList: LiveData<List<Actor>>
    private var liveImageMovie: LiveData<String>? = null
    private var liveMovieDescription: LiveData<String>? = null
    private var liveNameMovie: LiveData<String>? = null
    private var liveReview: LiveData<String>? = null
    private var liveGenre: LiveData<String>? = null
    private var liveMinimumAge: LiveData<String>? = null
    private var liveRating: LiveData<Float>? = null
    private var age: TextView? = null
    private var tagLine: TextView? = null
    private var title: TextView? = null
    private var review: TextView? = null
    private  var overview: TextView? = null
    private var movieImg: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, MoviesViewModelFactory()).get(FragmentMovieDetailsViewModel::class.java)
        val movieId = requireArguments().getInt(MOVIE_ID)
        viewModel.loadData(movieId)
        liveActorList = viewModel.getLiveActorList()

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_movies_details, container, false)
        age = view.findViewById(R.id.age)
        tagLine = view.findViewById(R.id.tagLine)
        title = view.findViewById(R.id.title)
        review = view.findViewById(R.id.review_count)
        overview = view.findViewById(R.id.storyLineContent)
        movieImg = view.findViewById(R.id.imageView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actorAdapter = ActorAdapter()

        val rvActors = view.findViewById<View>(R.id.rvActor) as RecyclerView
        rvActors.adapter = actorAdapter
        rvActors.addItemDecoration((SimpleDividerItemDecoration(25)))
        rvActors.layoutManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)

        viewModel.getLiveActorList().observe(this.viewLifecycleOwner, Observer<List<Actor>> {
            updateActors(view)
             })

        viewModel.getLiveGenre().observe(this.viewLifecycleOwner, { tagLine?.text = it})
        viewModel.getLiveMinimumAge().observe(this.viewLifecycleOwner, { age?.text = it + "+" })
        viewModel.getLiveNameMovie().observe(this.viewLifecycleOwner, { title?.text = it })
        viewModel.getLiveMovieDescription().observe(this.viewLifecycleOwner, { overview?.text = it })
        viewModel.getLiveReview().observe(this.viewLifecycleOwner, { review?.text = it })
        viewModel.getLiveImageMovie().observe(this.viewLifecycleOwner, {
            Glide.with(this)
                    .load(it)
                    .placeholder(R.drawable.base_line_movie)
                    .into(movieImg!!)
        })

        val title = viewModel.getLiveNameMovie().value

    }

    fun updateActors(view: View){

        if (liveActorList?.value?.isNotEmpty() == true) {
            liveActorList?.value?.let { actorAdapter?.updateActors(it) }
            actorAdapter?.updateActors(liveActorList!!.value)

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