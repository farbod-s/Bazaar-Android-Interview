package ir.cafebazaar.tmdb.data.remote.datasource

import ir.cafebazaar.tmdb.data.remote.api.MovieApiService
import ir.cafebazaar.tmdb.data.remote.model.MovieRemoteModel
import javax.inject.Inject

class DefaultMovieRemoteDataSource @Inject constructor(
    private val moviesApi: MovieApiService,
) : MovieRemoteDataSource {

    override suspend fun getMovies(): List<MovieRemoteModel> {
        return moviesApi.getUpcomingMovies().results
    }
}