package ir.cafebazaar.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.database.datasource.DefaultMovieLocalDataSource
import ir.cafebazaar.database.datasource.MovieLocalDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieLocalDataSource(
        defaultMovieLocalDataSource: DefaultMovieLocalDataSource
    ): MovieLocalDataSource
}