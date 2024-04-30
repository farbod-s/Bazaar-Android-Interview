package ir.cafebazaar.data.utils

import ir.cafebazaar.database.datasource.MovieLocalDataSource
import ir.cafebazaar.database.datasource.PreferencesLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class MovieCacheValidator @Inject constructor(
    private val preferencesDataSource: PreferencesLocalDataSource,
    private val localDataSource: MovieLocalDataSource,
) : CacheValidator {

    companion object {
        private const val CACHE_VALIDITY_TIME = 24 * 60 * 60 * 1000L // 24 hours in milliseconds
    }

    override suspend fun isValid(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastRefreshTime = preferencesDataSource.getLastFetchTimestamp()
        val isCacheOldEnough = (currentTime - lastRefreshTime) > CACHE_VALIDITY_TIME
        val isLocalStorageEmpty = localDataSource.getMovies().firstOrNull()?.isEmpty() ?: true

        return !isCacheOldEnough && !isLocalStorageEmpty
    }
}