package ir.cafebazaar.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.database.db.MovieDao
import ir.cafebazaar.database.db.MovieDatabase
import ir.cafebazaar.database.db.RemoteKeysDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase = MovieDatabase.getInstance(context)

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideRemoteKeysDao(database: MovieDatabase): RemoteKeysDao = database.remoteKeysDao()
}