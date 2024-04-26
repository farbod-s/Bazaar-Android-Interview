package ir.cafebazaar.tmdb.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.cafebazaar.tmdb.data.local.datasource.DefaultMovieLocalDataSource
import ir.cafebazaar.tmdb.data.local.datasource.MovieLocalDataSource
import ir.cafebazaar.tmdb.data.local.db.MovieDao
import ir.cafebazaar.tmdb.data.local.db.MovieDatabase
import ir.cafebazaar.tmdb.data.remote.api.AuthorizationInterceptor
import ir.cafebazaar.tmdb.data.remote.api.MovieApiService
import ir.cafebazaar.tmdb.data.remote.datasource.DefaultMovieRemoteDataSource
import ir.cafebazaar.tmdb.data.remote.datasource.MovieRemoteDataSource
import ir.cafebazaar.tmdb.data.repository.DefaultMovieRepository
import ir.cafebazaar.tmdb.data.repository.MovieRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        defaultMovieRepository: DefaultMovieRepository
    ): MovieRepository
}


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieLocalDataSource(
        defaultMovieLocalDataSource: DefaultMovieLocalDataSource
    ): MovieLocalDataSource

    @Binds
    abstract fun bindMovieRemoteDataSource(
        defaultMovieRemoteDataSource: DefaultMovieRemoteDataSource
    ): MovieRemoteDataSource
}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie-database",
    ).build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}