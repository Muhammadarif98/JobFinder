package com.example.jobfinder.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Vacancy
import com.example.jobfinder.component.BottomMenu
import com.example.jobfinder.component.VacancyCard
import com.example.jobfinder.ui.theme.JobFinderTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jobfinder.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favoriteVacancies by viewModel.favoriteVacancies.collectAsState()

    Scaffold(
        bottomBar = { BottomMenu(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (favoriteVacancies.isEmpty()) {
                Text(
                    text = "У вас пока нет избранных вакансий",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(favoriteVacancies) { vacancy ->
                        VacancyCard(
                            vacancy = vacancy,
                            onCardClick = {
                                navController.navigate("vacancy/${vacancy.id}")
                            },
                            onFavoriteClick = {
                                viewModel.removeFromFavorites(vacancy)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    JobFinderTheme {
        // Заглушка для превью
        val mockVacancies = listOf(
            Vacancy(
                id = "1",
                title = "UI/UX Designer",
                company = "Мобирикс",
                address = Address(town = "Минск"),
                experience = Experience(previewText = "Опыт от 1 до 3 лет", text = ""),
                publishedDate = "2024-02-20",
                isFavorite = true
            ),
            Vacancy(
                id = "2",
                title = "Web Developer",
                company = "Aston",
                address = Address(town = "Казань"),
                experience = Experience(previewText = "Опыт от 3 до 6 лет", text = ""),
                publishedDate = "2024-03-01",
                isFavorite = true
            )
        )
        FavoritesScreen(
            navController = rememberNavController(),
        )
    }
}