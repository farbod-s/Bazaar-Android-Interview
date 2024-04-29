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
import ir.cafebazaar.database.datasource.MovieLocalDataSource
import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.network.datasource.MovieRemoteDataSource

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    )

    @Provides
    fun provideMovieRemoteMediator(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource,
    ): RemoteMediator<Int, MovieLocalModel> = MovieRemoteMediator(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
    )

    @Provides
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