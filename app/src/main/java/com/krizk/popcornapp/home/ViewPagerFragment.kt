package com.krizk.popcornapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.krizk.popcornapp.databinding.FragmentViewPagerBinding
import com.krizk.popcornapp.home.movieList.MovieListFragment

enum class MovieCategories (val title: String) {
    Popular ("Popular Movies"),
    Top_rated ("Top Rated Movies")
}

class ViewPagerFragment : Fragment() {

    private val tabFragments = Array(MovieCategories.values().size) { i ->
        MovieListFragment.newInstance(
            MovieCategories.values()[i].toString().lowercase()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentViewPagerBinding.inflate(inflater)

        val adapter = ViewPagerAdapter(tabFragments, requireActivity().supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            tab.text = MovieCategories.values()[position].title

        }.attach()

        return binding.root
    }

}