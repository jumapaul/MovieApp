package com.example.movieapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.entity.MoviesEntity
import com.example.movieapp.domain.use_cases.PopularMovieUseCase
import com.example.movieapp.domain.use_cases.UpcomingMovieUsecase
import com.example.movieapp.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val upcomingMovieUsecase: UpcomingMovieUsecase
) : ViewModel() {

    private var _popularMoviesResponse = MutableLiveData(UIState<List<MoviesEntity>>())
    val popularMoviesResponse: LiveData<UIState<List<MoviesEntity>>> =
        _popularMoviesResponse

    private var _upcomingMovieResponse = MutableLiveData(UIState<List<MoviesEntity>>())
    val upcomingMovieResponse: LiveData<UIState<List<MoviesEntity>>> =
        _upcomingMovieResponse

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        popularMovieUseCase().onEach {
            if (it.data?.isNotEmpty() == true) {
                _popularMoviesResponse.value = UIState(data = it.data, isLoading = false)
            } else {
                _popularMoviesResponse.value =
                    UIState(data = it.data, isLoading = false, message = it.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        upcomingMovieUsecase().onEach {
            if (it.data?.isNotEmpty() == true) {
                _upcomingMovieResponse.value = UIState(data = it.data, isLoading = false)
            } else {
                _upcomingMovieResponse.value =
                    UIState(data = it.data, isLoading = false, message = it.message)
            }
        }.launchIn(viewModelScope)
    }


}