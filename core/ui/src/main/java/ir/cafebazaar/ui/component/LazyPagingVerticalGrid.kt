package ir.cafebazaar.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> LazyPagingVerticalGrid(
    items: LazyPagingItems<T>,
    columns: GridCells,
    itemContent: @Composable (T?) -> Unit,
    loadingContent: @Composable () -> Unit = { DefaultLoadingView() },
    errorContent: @Composable (String, () -> Unit) -> Unit = { message, retry ->
        DefaultErrorView(message, retry)
    },
    loadingItemContent: @Composable () -> Unit = { DefaultLoadingItem() },
    errorItemContent: @Composable (String, () -> Unit) -> Unit = { message, retry ->
        DefaultErrorItem(message, retry)
    }
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (items.loadState.refresh) {
            is LoadState.Loading -> {
                loadingContent()
            }

            is LoadState.Error -> {
                val e = items.loadState.refresh as LoadState.Error
                errorContent(e.error.message ?: "Unknown Error") {
                    items.refresh()
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = columns,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(count = items.itemCount) { index ->
                        items[index]?.let { movie ->
                            itemContent(movie)
                        }
                    }

                    when (items.loadState.append) {
                        is LoadState.Loading -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                loadingItemContent()
                            }
                        }

                        is LoadState.Error -> {
                            val e = items.loadState.append as LoadState.Error
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                errorItemContent(e.error.message ?: "Unknown Error") {
                                    items.retry()
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}