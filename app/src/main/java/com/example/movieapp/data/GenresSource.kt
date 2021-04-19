package com.example.movieapp.data

object GenresSource {
    private val mutableGenres: MutableMap<Int, Genre> = mutableMapOf()
    val genres: Map<Int, Genre> get() = mutableGenres

    fun saveGenres(genreList: List<Genre>) {
        genreList.forEach { genre ->
            mutableGenres[genre.id] = genre
        }
    }
}