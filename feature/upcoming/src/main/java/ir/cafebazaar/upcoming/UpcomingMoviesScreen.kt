package ir.cafebazaar.upcoming

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ir.cafebazaar.ui.component.AppBar
import ir.cafebazaar.ui.component.LazyPagingVerticalGrid
import kotlinx.coroutines.launch

@Composable
fun UpcomingMoviesScreen(
    viewModel: UpcomingMoviesViewModel = viewModel()
) {
    val items = viewModel.moviePagingDataFlow.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            if (items.loadState.refresh is LoadState.NotLoading) {
                AppBar()
            }
        },
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyPagingVerticalGrid(
                items = items,
                columns = GridCells.Adaptive(minSize = 96.dp),
                itemContent = { movie ->
                    movie?.let {
                        MovieItem(movie) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Clicked on: ${movie.title}",
                                    actionLabel = "OK",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}