package com.example.movieapp.data.local

import androidx.room.*
import com.example.movieapp.data.local.entity.MoviesEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MoviesEntity>

    @Query("SELECT * FROM movies where id =:id")
    suspend fun getMovie(id: Int): MoviesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: List<MoviesEntity>)
}