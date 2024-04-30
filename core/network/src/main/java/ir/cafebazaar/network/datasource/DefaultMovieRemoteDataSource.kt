package ir.cafebazaar.network.datasource

import ir.cafebazaar.network.api.MovieApiService
import ir.cafebazaar.network.model.MovieRemoteModel
import ir.cafebazaar.network.utils.ExceptionHandler
import javax.inject.Inject

class DefaultMovieRemoteDataSource @Inject constructor(
    private val moviesApi: MovieApiService,
    private val exceptionHandler: ExceptionHandler
) : MovieRemoteDataSource {

    override suspend fun getMovies(page: Int): List<MovieRemoteModel> {
        try {
            return moviesApi.getUpcomingMovies(page).results
        } catch (exception: Exception) {
            throw Exception(exceptionHandler.toHumanReadableMessage(exception))
        }
    }
}