package com.krizk.popcornapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.krizk.popcornapp.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    /**
     * Lazily initialize [MovieListViewModel].
     */
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMovieListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        val manager = GridLayoutManager(activity, 3)
        binding.moviesRecyclerView.layoutManager = manager

        val adapter = MovieListAdapter(MoviesListener { movieId ->

            val action =
                MovieListFragmentDirections.actionHomeFragmentToDetailFragment(movieId.toString())
            findNavController().navigate(action)
        })
        binding.moviesRecyclerView.adapter = adapter

        viewModel.allMovies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}