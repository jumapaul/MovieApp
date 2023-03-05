package com.example.movieapp.domain.repository

import com.example.movieapp.common.Resources
import com.example.movieapp.data.local.entity.MovieType
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.domain.model.MovieApiResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getApiPopularMovies(): MovieApiResponse
    suspend fun getLocalPopularMovies(): List<MoviesEntity>
    suspend fun saveMovies(movieApiResponse: MovieApiResponse,movieType: MovieType)
    suspend fun getApiUpcomingMovies(): MovieApiResponse
    suspend fun getLocalUpcomingMovies(): List<MoviesEntity>
    //suspend fun getMovie(id: Int): Flow<Resources<MoviesEntity>>
}