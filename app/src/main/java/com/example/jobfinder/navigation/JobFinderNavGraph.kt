package com.example.jobfinder.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobfinder.MainActivity
import com.example.jobfinder.screen.FavoritesScreen
import com.example.jobfinder.screen.HomeScreen
import com.example.jobfinder.screen.VacancyDetailsScreen
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun JobFinderNavGraph() {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                val viewModel: HomeViewModel = koinViewModel() // Получаем ViewModel через Koin
                HomeScreen(navController, viewModel)
            }

            composable("favorites") {
                val favoriteViewModel: FavoritesViewModel = koinViewModel()
                FavoritesScreen(
                    navController = navController,
                    viewModel = favoriteViewModel
                )
            }

            composable("vacancy/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                if (id != null) {
                    VacancyDetailsScreen(
                        navController = navController,
                        vacancyId = id
                    )
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun JobPreview() {
    JobFinderTheme {
        JobFinderNavGraph()
    }
}