package com.example.movieapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.Movie


class MoviesAdapter(private val clickListener: MovieClickListener?) : RecyclerView.Adapter<ViewHolderMovies>() {

    private var moviesList = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovies {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return ViewHolderMovies(movieView)
    }

    override fun onBindViewHolder(holder: ViewHolderMovies, position: Int) {
        holder.bind(moviesList[position])
        holder.itemView.setOnClickListener {
            clickListener?.clickOnItem(position)
        }

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateMovies(moviesList : List<Movie>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }
}

class ViewHolderMovies(view: View) : RecyclerView.ViewHolder(view) {
    private val age = itemView.findViewById<TextView>(R.id.age_textView)
    private val title = itemView.findViewById<TextView>(R.id.title_textView)
    private val tagLine = itemView.findViewById<TextView>(R.id.tags)
    private val moviePic = itemView.findViewById<ImageView>(R.id.movie_pic)
    private val reviews = itemView.findViewById<TextView>(R.id.review)
    private val duration = itemView.findViewById<TextView>(R.id.duration)

    fun bind(movie: Movie) {

        age.text = movie.minimumAge.toString()
        title.text = movie.title
        tagLine.text = movie.genres
        reviews.text = movie.numberOfRatings.toString()
        duration.text = movie.runtime.toString()


        Glide.with(itemView.context)
            .load(movie.poster)
            .placeholder(R.drawable.base_line_movie)
            .centerCrop()
            .into(moviePic)
    }
}