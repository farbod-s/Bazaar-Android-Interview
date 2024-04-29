package ir.cafebazaar.network.api

import ir.cafebazaar.network.model.UpcomingMoviesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): UpcomingMoviesRemoteModel
}