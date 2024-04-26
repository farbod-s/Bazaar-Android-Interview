package ir.cafebazaar.tmdb.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY uuid DESC")
    fun getMovies(): Flow<List<MovieLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(items: List<MovieLocalModel>)
}