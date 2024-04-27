package ir.cafebazaar.tmdb.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.cafebazaar.tmdb.data.model.Movie

@Composable
fun UpcomingMoviesScreen(
    viewModel: UpcomingMoviesViewModel = viewModel(),
    snackbarHost: SnackbarHostState,
) {
    val items = viewModel.moviePagingDataFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = items.itemCount,
                ) { index ->
                    items[index]?.let { movie ->
                        MovieItem(
                            item = movie,
                            snackbarHost = snackbarHost,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                item {
                    when (items.loadState.append) {
                        is LoadState.Loading -> {
                            // Show loading at the bottom of the list
                            LoadingItem()
                        }

                        is LoadState.Error -> {
                            // Show error message and retry button
                            ErrorItem(items)
                        }

                        else -> {
                            Text("Reached end of content")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    item: Movie,
    snackbarHost: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val clickState = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .background(color = Color.LightGray)
            .clickable { clickState.value = true }
    ) {
        Text(
            text = item.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(16.dp)
        )
        // Add image loading logic for item.cover here
    }

    LaunchedEffect(clickState.value) {
        if (clickState.value) {
            showSnackbar(snackbarHost, "Clicked on: ${item.title}")
            clickState.value = false // Reset the state
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(items: LazyPagingItems<Movie>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed to load more items.", color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { items.retry() }
        ) {
            Text("Retry")
        }
    }
}

suspend fun showSnackbar(snackbarHost: SnackbarHostState, message: String) {
    snackbarHost.showSnackbar(
        message = message,
        actionLabel = "Dismiss",
        duration = SnackbarDuration.Short
    )
}