package com.example.movieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.common.Constants.LargeImage
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.databinding.MovieReycleviewBinding
import com.example.movieapp.presentation.UIState

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {
    lateinit var binding: MovieReycleviewBinding

    var movies: MutableList<MoviesEntity> = ArrayList()

    inner class PopularMoviesViewHolder(binding: MovieReycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesEntity: MoviesEntity) {
            binding.apply {
                tvMovieName.text = moviesEntity.title
                if(moviesEntity.backdrop_path!=null){
                    Glide.with(binding.root).load("${LargeImage}${moviesEntity.backdrop_path}")
                        .into(ivImageIcon)
                }else{
                    Glide.with(binding.root).load("${LargeImage}${moviesEntity.poster_path}")
                        .into(ivImageIcon)
                }
            }
        }
    }

    fun addItem(data: List<MoviesEntity>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        binding = MovieReycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}