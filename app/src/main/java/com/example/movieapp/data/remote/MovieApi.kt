package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.trending.Trending
import com.example.movieapp.domain.model.MovieApiResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("3/trending/all/day")
    suspend fun getTrendingMovies(): List<Trending>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): MovieApiResponse

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMoves(): MovieApiResponse
}