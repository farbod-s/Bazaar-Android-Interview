package ir.cafebazaar.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ir.cafebazaar.ui.theme.TMDBTheme
import ir.cafebazaar.upcoming.UpcomingMoviesScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                UpcomingMoviesScreen()
            }
        }
    }
}