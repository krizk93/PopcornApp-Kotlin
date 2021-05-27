package com.krizk.popcornapp.home.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.krizk.popcornapp.databinding.FragmentMovieListBinding
import com.krizk.popcornapp.home.MovieCategories
import com.krizk.popcornapp.home.ViewPagerFragmentDirections


// the fragment initialization parameters
private const val ARG_CATEGORY = "category"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class MovieListFragment : Fragment() {

    private lateinit var category: String


    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY) ?: MovieCategories.Popular.toString().lowercase()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMovieListBinding.inflate(inflater)

        viewModelFactory = MovieListViewModelFactory(category)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MovieListViewModel
        binding.viewModel = viewModel

        val manager = GridLayoutManager(activity, 3)
        binding.moviesRecyclerView.layoutManager = manager

        val adapter = MovieListAdapter(MoviesListener { movieId ->

            val action =
                ViewPagerFragmentDirections.actionViewPagerFragmentToMovieDetailFragment(movieId.toString())
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category The category of movies to call from the API.
         * @return A new instance of fragment MovieListFragment.
         */
        @JvmStatic
        fun newInstance(category: String) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
    }
}