package com.krizk.popcornapp.home.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListViewModelFactory(private val category: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}