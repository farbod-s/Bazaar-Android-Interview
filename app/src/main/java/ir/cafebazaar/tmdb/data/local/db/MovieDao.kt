package ir.cafebazaar.tmdb.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY uuid ASC")
    fun getMovies(): Flow<List<MovieLocalModel>>

    @Query("SELECT * FROM movies")
    fun pagingSource(): PagingSource<Int, MovieLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(items: List<MovieLocalModel>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}