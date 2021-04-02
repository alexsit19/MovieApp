package com.example.movieapp.data.network

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class Network {

    private val baseUrl = "https://api.themoviedb.org/3/"
    private val api_key = "e65ad475d75413043d534a5746a8cbbf"
    private var page = "1"
    private var movie_top_rated = "movie/top_rated"

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl)
        //можно добавить конвертер фектори слайд 148
        .client(httpClient)
        .build()



}

interface MovieDBApi {
    @GET("movie/top_rate")
    fun getTopRatedMovies(): Call<List<MovieLIstResponse>>

}