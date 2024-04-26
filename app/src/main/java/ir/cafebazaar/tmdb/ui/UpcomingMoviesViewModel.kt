package ir.cafebazaar.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.cafebazaar.tmdb.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UpcomingMoviesUiState())
    val uiState: StateFlow<UpcomingMoviesUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                val movies = repository.getUpcomingMovies()
                movies.collect { upcomingMovies ->
                    _uiState.update {
                        it.copy(movieItems = upcomingMovies.map {
                            MovieItemUiState(
                                it.title,
                                it.cover
                            )
                        })
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(userMessage = "Error fetching movies: ${e.message}") }
            }
        }
    }
}