package com.krizk.popcornapp.home

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

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMovies()
    }

    private fun getMovies() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = MoviesApi.retrofitService.getMovies("popular", BuildConfig.ApiKey)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {

                        val allMovies: List<Movies.Result> = response.body()?.results ?: emptyList()
                        _response.value = allMovies[0].title
                    }

                } catch (e: Throwable) {

                    _response.value = "Error:" + e.message
                }
            }
        }
    }
}