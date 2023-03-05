package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.common.Constants.BASE_URL
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.TokenInterceptor
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .create()

    @Provides
    @Singleton
    fun provideMoveApi(gson: Gson): MovieApi {
        return Retrofit.Builder()
            .client(provideInterceptor())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieApi, db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(api = api, db.movieDao)
    }

    @Singleton
    @Provides
    fun provideInterceptor(): OkHttpClient {
        val interceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
            .addInterceptor(interceptor).build()
    }
}