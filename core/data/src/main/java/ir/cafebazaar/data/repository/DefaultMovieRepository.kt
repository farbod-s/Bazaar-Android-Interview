package ir.cafebazaar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import ir.cafebazaar.data.di.IoDispatcher
import ir.cafebazaar.data.model.Movie
import ir.cafebazaar.data.model.asExternalModel
import ir.cafebazaar.database.model.MovieLocalModel
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