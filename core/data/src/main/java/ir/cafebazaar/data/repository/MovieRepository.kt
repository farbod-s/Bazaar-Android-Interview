package ir.cafebazaar.data.repository

import androidx.paging.PagingData
import ir.cafebazaar.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
}