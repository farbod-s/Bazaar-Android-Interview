package ir.cafebazaar.tmdb.ui

data class UpcomingMoviesUiState(
    val movieItems: List<MovieItemUiState> = listOf(),
    val userMessage: String? = null,
)

data class MovieItemUiState(
    val title: String,
    val cover: String,
)