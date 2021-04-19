package com.example.movieapp.data.converter

import com.example.movieapp.data.Genre
import com.example.movieapp.data.GenreResponseItem

class GenreMapper {
    fun toGenre(genresResponseItem: GenreResponseItem): Genre = Genre(genresResponseItem.id, genresResponseItem.name)
}