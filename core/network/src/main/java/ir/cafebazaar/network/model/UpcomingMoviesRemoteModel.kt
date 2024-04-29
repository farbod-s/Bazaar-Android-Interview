package ir.cafebazaar.network.model

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesRemoteModel(

    @SerializedName("dates")
    val dates: DatesRemoteModel? = null,

    @SerializedName("page")
    val page: Int = 1,

    @SerializedName("results")
    val results: List<MovieRemoteModel> = listOf(),

    @SerializedName("total_pages")
    val totalPages: Int = 1,

    @SerializedName("total_results")
    val totalResults: Int = 0,
)

data class DatesRemoteModel(

    @SerializedName("maximum")
    val maximum: String? = null,

    @SerializedName("minimum")
    val minimum: String? = null,
)