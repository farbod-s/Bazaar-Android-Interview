package ir.cafebazaar.network.datasource

import ir.cafebazaar.network.model.MovieRemoteModel

interface MovieRemoteDataSource {
    suspend fun getMovies(page: Int): List<MovieRemoteModel>
}