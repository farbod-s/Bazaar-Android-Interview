package ir.cafebazaar.network.datasource

import ir.cafebazaar.network.api.MovieApiService
import ir.cafebazaar.network.model.MovieRemoteModel
import javax.inject.Inject

class DefaultMovieRemoteDataSource @Inject constructor(
    private val moviesApi: MovieApiService,
) : MovieRemoteDataSource {

    override suspend fun getMovies(page: Int): List<MovieRemoteModel> {
        return moviesApi.getUpcomingMovies(page).results
    }
}