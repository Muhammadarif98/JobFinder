package com.example.jobfinder.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobfinder.MainActivity
import com.example.jobfinder.screen.FavoritesScreen
import com.example.jobfinder.screen.HomeScreen
import com.example.jobfinder.screen.MessagesScreen
import com.example.jobfinder.screen.ProfileScreen
import com.example.jobfinder.screen.ResponsesScreen
import com.example.jobfinder.screen.VacancyDetailsScreen
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun JobFinderNavGraph() {
    val navController = rememberNavController()
    val favoriteViewModel: FavoritesViewModel = koinViewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            // Главный экран
            composable("home") {
                val viewModel: HomeViewModel = koinViewModel()
                HomeScreen(navController, viewModel)
            }

            // Избранное
            composable("favorites") {

                FavoritesScreen(
                    navController = navController,
                    viewModel = favoriteViewModel
                )
            }

            // Детали вакансии
            composable("vacancy/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                if (id != null) {
                    VacancyDetailsScreen(
                        navController = navController,
                        vacancyId = id,
                        favoriteViewModel = favoriteViewModel
                    )
                }
            }

            // Отклики
            composable("responses") {
                ResponsesScreen(navController = navController,viewModel = favoriteViewModel)
            }

            // Сообщения
            composable("messages") {
                MessagesScreen(navController = navController,viewModel = favoriteViewModel)
            }

            // Профиль
            composable("profile") {
                ProfileScreen(navController = navController,viewModel = favoriteViewModel)
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