package com.example.movieapp.presentation

data class UIState<T>(
    var data: T? = null,
    var message: String? = null,
    var isLoading: Boolean = false
)