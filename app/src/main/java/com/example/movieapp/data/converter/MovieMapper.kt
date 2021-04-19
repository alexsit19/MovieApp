package com.example.movieapp.data.converter

import com.example.movieapp.data.Genre
import com.example.movieapp.data.Movie
import com.example.movieapp.data.network.MovieNetworkModel
import java.lang.IllegalArgumentException

class MovieMapper {
    fun toMovie(
            jsonMovie: MovieNetworkModel,
            genresMap: Map<Int, Genre>
    ) = Movie(id = jsonMovie.id,
            title = jsonMovie.title,
            overview = jsonMovie.overview,
            poster = jsonMovie.poster_path,
            ratings = jsonMovie.vote_average,
            numberOfRatings = jsonMovie.vote_count,
            minimumAge = if (jsonMovie.adult) "16+" else "13+",
            genres = jsonMovie.genre_ids
                    .joinToString {
                        genresMap[it]?.name ?: throw IllegalArgumentException("Genre not found")
                    }
    )
}