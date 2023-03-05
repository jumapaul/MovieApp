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

class UpcomingMovieUsecase @Inject constructor(val repository: MovieRepository) {

    operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            val movie = repository.getApiUpcomingMovies()
            repository.saveMovies(movie, movieType = MovieType.UPCOMING)

            emit(
                Resources.Success(data = repository.getLocalUpcomingMovies())
            )

        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalUpcomingMovies(),
                    message = "An error occurred"
                )
            )

        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalUpcomingMovies(),
                    message = "You have no internet connection"
                )
            )
        }
    }
}