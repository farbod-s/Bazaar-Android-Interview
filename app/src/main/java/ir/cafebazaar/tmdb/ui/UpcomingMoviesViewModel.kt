package ir.cafebazaar.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.cafebazaar.tmdb.data.model.Movie
import ir.cafebazaar.tmdb.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    repository: MovieRepository,
) : ViewModel() {

    val moviePagingDataFlow: Flow<PagingData<Movie>> = repository.getUpcomingMovies()
        .cachedIn(viewModelScope)
}