package ir.cafebazaar.tmdb.data.repository

import ir.cafebazaar.tmdb.data.di.IoDispatcher
import ir.cafebazaar.tmdb.data.local.datasource.MovieLocalDataSource
import ir.cafebazaar.tmdb.data.local.model.asExternalModel
import ir.cafebazaar.tmdb.data.model.Movie
import ir.cafebazaar.tmdb.data.remote.datasource.MovieRemoteDataSource
import ir.cafebazaar.tmdb.data.remote.model.asLocalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override suspend fun getUpcomingMovies(): Flow<List<Movie>> = withContext(ioDispatcher) {
        val movies = remoteDataSource.getMovies().map { it.asLocalModel() }
        localDataSource.saveMovies(movies)
        return@withContext localDataSource.getMovies().map { it.map { it.asExternalModel() } }
    }
}