package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.Datesdto

data class MovieApiResponse(
    val dates: Datesdto,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
