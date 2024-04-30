package ir.cafebazaar.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.data.repository.MovieRemoteMediator
import ir.cafebazaar.data.utils.CacheValidator
import ir.cafebazaar.database.datasource.MovieLocalDataSource
import ir.cafebazaar.database.datasource.PreferencesLocalDataSource
import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.network.datasource.MovieRemoteDataSource
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    @Singleton
    fun providePagingConfig() = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    )

    @Provides
    @Singleton
    fun provideMovieRemoteMediator(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource,
        preferencesLocalDataSource: PreferencesLocalDataSource,
        cacheValidator: CacheValidator
    ): RemoteMediator<Int, MovieLocalModel> = MovieRemoteMediator(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        preferencesLocalDataSource = preferencesLocalDataSource,
        cacheValidator = cacheValidator
    )

    @Provides
    @Singleton
    fun provideMoviePager(
        pagingConfig: PagingConfig,
        movieRemoteMediator: MovieRemoteMediator,
        localDataSource: MovieLocalDataSource
    ): Pager<Int, MovieLocalModel> = Pager(
        config = pagingConfig,
        remoteMediator = movieRemoteMediator,
        pagingSourceFactory = {
            localDataSource.pagingSource()
        },
    )
}