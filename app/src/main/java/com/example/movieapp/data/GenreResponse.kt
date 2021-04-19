package com.example.movieapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GenreResponse (@SerialName("genres") val genres: List<GenreResponseItem>)

@Serializable
class GenreResponseItem(@SerialName("id") val id: Int, @SerialName("name") val name: String)