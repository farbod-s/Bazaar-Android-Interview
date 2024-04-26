package ir.cafebazaar.tmdb.data.local.datasource

import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun getMovies(): Flow<List<MovieLocalModel>>

    suspend fun saveMovies(movies: List<MovieLocalModel>)
}