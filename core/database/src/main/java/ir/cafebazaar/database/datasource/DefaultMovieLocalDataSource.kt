package ir.cafebazaar.database.datasource

import androidx.paging.PagingSource
import androidx.room.withTransaction
import ir.cafebazaar.database.db.MovieDao
import ir.cafebazaar.database.db.MovieDatabase
import ir.cafebazaar.database.db.RemoteKeysDao
import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.database.model.RemoteKeys
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMovieLocalDataSource @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val movieDao: MovieDao,
    private val remoteKeysDao: RemoteKeysDao,
) : MovieLocalDataSource {

    override suspend fun getMovies(): Flow<List<MovieLocalModel>> {
        return movieDao.getMovies()
    }

    override suspend fun saveMovies(movies: List<MovieLocalModel>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun clearMovies() {
        movieDao.clearMovies()
    }

    override suspend fun getRemoteKeysByMovieId(movieId: Long): RemoteKeys? {
        return remoteKeysDao.remoteKeysByMovieId(movieId)
    }

    override suspend fun saveRemoteKeys(keys: List<RemoteKeys>) {
        remoteKeysDao.insertRemoteKeys(keys)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeysDao.clearRemoteKeys()
    }

    override suspend fun withTransaction(operation: suspend () -> Unit) {
        movieDatabase.withTransaction {
            operation()
        }
    }

    override fun pagingSource(): PagingSource<Int, MovieLocalModel> {
        return movieDao.pagingSource()
    }
}