package com.example.movieapp.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MainActivity
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.presentation.adapters.PopularMoviesAdapter
import com.example.movieapp.presentation.adapters.UpcomingMovieAdapter
import com.example.movieapp.presentation.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val popularMovieAdapter: PopularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private val upcomingMovieAdapter: UpcomingMovieAdapter by lazy { UpcomingMovieAdapter() }
    lateinit var binding: FragmentMoviesBinding

    private val movieViewModel:MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        observePopularAdapter()
        observeUpcomingAdapter()

        return binding.root
    }

    fun observePopularAdapter() {
        movieViewModel.popularMoviesResponse.observe(viewLifecycleOwner) {
            popularMovieAdapter.addItem(it.data ?: emptyList())
            setPopularMoviesRecycleview()
        }
    }

    fun setPopularMoviesRecycleview() {
        binding.rvPopular.apply {
            hasFixedSize()
            adapter = popularMovieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun observeUpcomingAdapter(){
        movieViewModel.upcomingMovieResponse.observe(viewLifecycleOwner){
            upcomingMovieAdapter.addItems(it.data?: emptyList())
            setUpComingMoviesRecycleview()
        }
    }

    fun setUpComingMoviesRecycleview() {
        binding.rvUpcoming.apply {
            hasFixedSize()
            adapter = upcomingMovieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}