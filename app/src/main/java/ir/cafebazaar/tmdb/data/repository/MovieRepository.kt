package ir.cafebazaar.tmdb.data.repository

import androidx.paging.PagingData
import ir.cafebazaar.tmdb.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
}