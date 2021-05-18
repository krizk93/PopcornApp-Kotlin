package com.krizk.popcornapp.home

import android.content.pm.ModuleInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krizk.popcornapp.BuildConfig
import com.krizk.popcornapp.network.Movies
import com.krizk.popcornapp.network.MoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMovies()
    }

    private fun getMovies() {

        MoviesApi.retrofitService.getMovies("popular", BuildConfig.ApiKey)
            .enqueue(object :
                Callback<Movies> {

                override fun onFailure(call: Call<Movies>, t: Throwable) {

                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {

                    val allMovies: List<Movies.Result> = response.body()?.results ?: emptyList()
                    _response.value = allMovies[0].title
                }

            })
    }
}