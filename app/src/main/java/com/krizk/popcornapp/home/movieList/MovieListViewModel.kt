package com.krizk.popcornapp.home.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krizk.popcornapp.BuildConfig
import com.krizk.popcornapp.network.Movies
import com.krizk.popcornapp.network.MoviesApi
import kotlinx.coroutines.launch


class MovieListViewModel(category: String) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movies.Result>>()

    val allMovies: LiveData<List<Movies.Result>>
        get() = _allMovies

    init {
        getMovies(category)
    }

    private fun getMovies(category: String) {


        viewModelScope.launch {

            try {
                val response = MoviesApi.retrofitService.getMovies(category, BuildConfig.ApiKey)

                if (response.isSuccessful) {
                    _allMovies.value = response.body()?.results ?: emptyList()
                } else {
                    Log.e("MovieListViewModel", response.errorBody().toString())
                }

            } catch (e: Exception) {

                Log.e("MovieListViewModel", "Error: ", e)
            }
        }
    }
}