package ir.cafebazaar.tmdb.data.local.datasource

import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import ir.cafebazaar.tmdb.data.local.model.RemoteKeys
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun getMovies(): Flow<List<MovieLocalModel>>

    suspend fun saveMovies(movies: List<MovieLocalModel>)

    suspend fun clearMovies()

    suspend fun getRemoteKeysByMovieId(movieId: Long): RemoteKeys?

    suspend fun saveRemoteKeys(keys: List<RemoteKeys>)

    suspend fun clearRemoteKeys()

    suspend fun withTransaction(operation: suspend () -> Unit)
}