package com.krizk.popcornapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailViewModelFactory(private val movieID: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}