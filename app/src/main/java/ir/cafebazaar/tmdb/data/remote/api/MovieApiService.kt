package ir.cafebazaar.tmdb.data.remote.api

import ir.cafebazaar.tmdb.data.remote.model.UpcomingMoviesRemoteModel
import retrofit2.http.GET

interface MovieApiService {

    @GET("upcoming")
    suspend fun getUpcomingMovies(): UpcomingMoviesRemoteModel
}