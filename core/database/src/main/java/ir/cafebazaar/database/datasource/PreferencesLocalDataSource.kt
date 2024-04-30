package ir.cafebazaar.database.datasource

interface PreferencesLocalDataSource {
    suspend fun getLastFetchTimestamp(): Long

    suspend fun setLastFetchTimestamp(time: Long)
}