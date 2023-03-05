package com.example.movieapp.domain.model

import com.example.movieapp.data.local.entity.MovieType
import com.example.movieapp.data.local.entity.MoviesEntity

data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMovieEntity(movieType: MovieType): MoviesEntity {
        return MoviesEntity(
            id,
            adult,
            backdrop_path,
//            genre_ids,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count,
            movieType.name
        )
    }
}