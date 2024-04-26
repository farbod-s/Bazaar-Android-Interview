package ir.cafebazaar.tmdb.data.repository

import ir.cafebazaar.tmdb.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getUpcomingMovies(): Flow<List<Movie>>
}