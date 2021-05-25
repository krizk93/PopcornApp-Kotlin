package com.krizk.popcornapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krizk.popcornapp.databinding.MovieItemViewBinding
import com.krizk.popcornapp.network.Movies

class MovieListAdapter(private val clickListener: MoviesListener) :
    ListAdapter<Movies.Result, MovieListAdapter.MoviesViewHolder>(MovieListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        return MoviesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    class MoviesViewHolder private constructor(private val binding: MovieItemViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Movies.Result, clickListener: MoviesListener) {

            binding.movie = item
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): MoviesViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemViewBinding.inflate(layoutInflater, parent, false)
                return MoviesViewHolder(binding)
            }

    }

    }
}

class MovieListDiffCallback : DiffUtil.ItemCallback<Movies.Result>() {

    override fun areItemsTheSame(oldItem: Movies.Result, newItem: Movies.Result): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies.Result, newItem: Movies.Result): Boolean {

        return oldItem == newItem
    }

}

class MoviesListener(val clickListener: (movieId: Int) -> Unit) {

    fun onClick(movie: Movies.Result) = clickListener(movie.id)
}