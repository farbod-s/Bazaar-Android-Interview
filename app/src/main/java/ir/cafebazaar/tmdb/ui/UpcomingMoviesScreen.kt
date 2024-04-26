package ir.cafebazaar.tmdb.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpcomingMoviesScreen(
    viewModel: UpcomingMoviesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(color = Color.LightGray) {
        Column {
            if (uiState.userMessage != null) {
                Text(
                    text = uiState.userMessage!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.Red
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.movieItems) { movieItem ->
                        MovieListItem(
                            item = movieItem,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }

                    if (uiState.movieItems.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieListItem(item: MovieItemUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .background(color = Color.LightGray)
    ) {
        Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        // Add image loading logic for item.cover here
    }
}