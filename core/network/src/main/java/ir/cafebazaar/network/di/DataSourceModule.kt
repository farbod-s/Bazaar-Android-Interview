package ir.cafebazaar.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.network.datasource.DefaultMovieRemoteDataSource
import ir.cafebazaar.network.datasource.MovieRemoteDataSource
import ir.cafebazaar.network.utils.ExceptionHandler
import ir.cafebazaar.network.utils.MovieExceptionHandler

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieRemoteDataSource(
        defaultMovieRemoteDataSource: DefaultMovieRemoteDataSource
    ): MovieRemoteDataSource

    @Binds
    abstract fun bindMovieExceptionHandler(
        movieExceptionHandler: MovieExceptionHandler
    ): ExceptionHandler
}