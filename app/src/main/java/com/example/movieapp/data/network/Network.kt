package com.example.movieapp.data.network

import com.example.movieapp.data.ActorsInfo
import com.example.movieapp.data.ApiResponse
import com.example.movieapp.data.Movie

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL ="https://api.themoviedb.org/3/"
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getMovies( @Query("api_key") apiKey: String): ApiResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int, @Query( "api_key") apiKey: String): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): ActorsInfo
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}



