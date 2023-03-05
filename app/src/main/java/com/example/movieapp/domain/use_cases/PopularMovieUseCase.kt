package com.example.movieapp.domain.use_cases

import com.example.movieapp.common.Resources
import com.example.movieapp.data.local.entity.MovieType
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            //get data from api
            val apiResponse = repository.getApiPopularMovies()
            //save data to db
            repository.saveMovies(apiResponse, MovieType.POPULAR)
            //emit saved saved movies
            emit(Resources.Success(data = repository.getLocalPopularMovies()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = "An error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = "NO internet connection"
                )
            )
        }
    }
}