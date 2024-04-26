package ir.cafebazaar.tmdb.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.cafebazaar.tmdb.data.local.model.MovieLocalModel

@Database(entities = [MovieLocalModel::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}