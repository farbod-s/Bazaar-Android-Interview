package ir.cafebazaar.tmdb.data.remote.datasource

import ir.cafebazaar.tmdb.data.remote.model.MovieRemoteModel

interface MovieRemoteDataSource {
    suspend fun getMovies(): List<MovieRemoteModel>
}