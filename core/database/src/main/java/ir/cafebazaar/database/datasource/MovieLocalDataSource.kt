package ir.cafebazaar.database.datasource

import androidx.paging.PagingSource
import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.database.model.RemoteKeys
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun getMovies(): Flow<List<MovieLocalModel>>

    suspend fun saveMovies(movies: List<MovieLocalModel>)

    suspend fun clearMovies()

    suspend fun getRemoteKeysByMovieId(movieId: Long): RemoteKeys?

    suspend fun saveRemoteKeys(keys: List<RemoteKeys>)

    suspend fun clearRemoteKeys()

    suspend fun withTransaction(operation: suspend () -> Unit)

    fun pagingSource(): PagingSource<Int, MovieLocalModel>
}