package com.example.movietime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietime.presentation.moviescreen.MovieScreen
import com.example.movietime.presentation.moviescreen.MovieViewModel
import com.example.movietime.ui.theme.MovieTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTimeTheme {
                Surface(Modifier.fillMaxSize().padding()) {
                    val viewModel = hiltViewModel<MovieViewModel>()
                    val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()
                    MovieScreen(movies)
                }
            }
        }
    }
}



