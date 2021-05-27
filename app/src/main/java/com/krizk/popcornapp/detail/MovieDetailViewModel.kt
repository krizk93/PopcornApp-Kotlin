package com.krizk.popcornapp.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krizk.popcornapp.BuildConfig
import com.krizk.popcornapp.network.MovieDetails
import com.krizk.popcornapp.network.MoviesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(movieID: String) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    init {
        getMovieDetails(movieID)
    }

    private fun getMovieDetails(movieID: String) {

        viewModelScope.launch {

            try {
                val response =
                    MoviesApi.retrofitService.getMovieDetails(movieID, BuildConfig.ApiKey)

                if (response.isSuccessful) {
                    _movieDetails.value = response.body()
                } else {
                    Log.e("MovieDetailViewModel", response.errorBody().toString())
                }

            } catch (e: Exception) {
                Log.e("MovieDetailViewModel", "Error: ", e)
            }
        }
    }

}