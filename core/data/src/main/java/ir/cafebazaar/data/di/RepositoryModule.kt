package ir.cafebazaar.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.data.repository.DefaultMovieRepository
import ir.cafebazaar.data.repository.MovieRepository
import ir.cafebazaar.data.utils.CacheValidator
import ir.cafebazaar.data.utils.MovieCacheValidator

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        defaultMovieRepository: DefaultMovieRepository
    ): MovieRepository

    @Binds
    abstract fun bindMovieCacheValidator(
        movieCacheValidator: MovieCacheValidator
    ): CacheValidator
}