package com.example.movieapp.data.repository

import com.example.movieapp.common.Resources
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.local.entity.MovieType
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.model.MovieApiResponse
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getApiPopularMovies(): MovieApiResponse = api.getPopularMovies()

    override suspend fun getLocalPopularMovies(): List<MoviesEntity> =
        dao.getAllMovies().filter { it.movieType == MovieType.POPULAR.name }

    override suspend fun getApiUpcomingMovies(): MovieApiResponse {
        return api.getUpcomingMoves()
    }

    override suspend fun getLocalUpcomingMovies(): List<MoviesEntity> {
       return dao.getAllMovies().filter { it.movieType == MovieType.UPCOMING.name }
    }

    override suspend fun saveMovies(movieApiResponse: MovieApiResponse, movieType: MovieType) {
        dao.addMovie(movieApiResponse.results.map { it.toMovieEntity(movieType) })
    }
}