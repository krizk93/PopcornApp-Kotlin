package com.krizk.popcornapp.home.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krizk.popcornapp.BuildConfig
import com.krizk.popcornapp.network.Movies
import com.krizk.popcornapp.network.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieListViewModel(category: String) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movies.Result>>()

    val allMovies: LiveData<List<Movies.Result>>
        get() = _allMovies

    init {
        getMovies(category)
    }

    private fun getMovies(category: String) {


        CoroutineScope(Dispatchers.IO).launch {
            val response = MoviesApi.retrofitService.getMovies(category, BuildConfig.ApiKey)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {

                        _allMovies.value = response.body()?.results ?: emptyList()
                    }

                } catch (e: Throwable) {

                    Log.e("network call", "Error: ", e)
                }
            }
        }
    }
}