package ir.cafebazaar.tmdb.data.local.datasource

import ir.cafebazaar.tmdb.data.local.db.MovieDao
import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
) : MovieLocalDataSource {

    override suspend fun getMovies(): Flow<List<MovieLocalModel>> {
        return movieDao.getMovies()
    }

    override suspend fun saveMovies(movies: List<MovieLocalModel>) {
        movieDao.insertMovies(movies)
    }
}