package com.krizk.popcornapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.krizk.popcornapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater)

        viewModelFactory = MovieDetailViewModelFactory(args.movieId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}