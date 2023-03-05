package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 4, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object{
        const val DATABASE_NAME = "movie.db"
    }
}