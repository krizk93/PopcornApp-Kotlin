package com.krizk.popcornapp.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krizk.popcornapp.BuildConfig
import com.krizk.popcornapp.network.MovieDetails
import com.krizk.popcornapp.network.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(movieID: String) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    init {
        getMovieDetails(movieID)
    }

    private fun getMovieDetails(movieID: String) {

        CoroutineScope(Dispatchers.IO).launch {

            val response = MoviesApi.retrofitService.getMovieDetails(movieID, BuildConfig.ApiKey)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {

                        _movieDetails.value = response.body()
                    }

                } catch (e: Throwable) {

                    Log.e("network call", "Error: ", e)
                }
            }
        }

    }

}