package com.krizk.popcornapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.themoviedb.org/"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()


interface ApiService {
    @GET("/3/movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String
    ): Response<Movies>
}

object MoviesApi {

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}