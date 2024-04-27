package ir.cafebazaar.tmdb.data.remote.api

import ir.cafebazaar.tmdb.data.remote.model.UpcomingMoviesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): UpcomingMoviesRemoteModel
}