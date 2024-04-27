package ir.cafebazaar.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import ir.cafebazaar.tmdb.data.di.IoDispatcher
import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import ir.cafebazaar.tmdb.data.local.model.asExternalModel
import ir.cafebazaar.tmdb.data.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val moviePager: Pager<Int, MovieLocalModel>,
) : MovieRepository {

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return moviePager.flow.map { pagingData ->
            pagingData.map { it.asExternalModel() }
        }.flowOn(ioDispatcher)
    }
}