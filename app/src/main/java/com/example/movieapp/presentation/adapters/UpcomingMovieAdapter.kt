package com.example.movieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.common.Constants
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.databinding.MovieReycleviewBinding

class UpcomingMovieAdapter : RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMoviesViewHolder>() {

    lateinit var binding: MovieReycleviewBinding
    val movies: MutableList<MoviesEntity> = ArrayList()

    inner class UpcomingMoviesViewHolder(binding: MovieReycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesEntity: MoviesEntity) {
            binding.apply {
                tvMovieName.text = moviesEntity.title
                if(moviesEntity.backdrop_path!=null){
                    Glide.with(binding.root).load("${Constants.LargeImage}${moviesEntity.backdrop_path}")
                        .into(ivImageIcon)
                }else{
                    Glide.with(binding.root).load("${Constants.LargeImage}${moviesEntity.poster_path}")
                        .into(ivImageIcon)
                }
            }
        }
    }

    fun addItems(data: List<MoviesEntity>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        binding = MovieReycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}