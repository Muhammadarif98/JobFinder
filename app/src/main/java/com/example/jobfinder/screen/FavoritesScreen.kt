package com.example.jobfinder.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Salary
import com.example.domain.model.Vacancy
import com.example.jobfinder.component.BottomMenu
import com.example.jobfinder.component.VacancyCard
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }
    // Получаем список избранных вакансий
    val favoriteVacancies by viewModel.favoriteVacancies.collectAsState()

    // Количество избранных вакансий
    val favoriteCount = favoriteVacancies.size

    Scaffold(
        bottomBar = {
            BottomMenu(
                navController = navController,
                favoriteCount = favoriteCount // Передаем количество избранных
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (favoriteVacancies.isEmpty()) {
                Text(
                    text = "У вас пока нет избранных вакансий",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    item {
                        Text(
                            "Избранное", fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    item {
                        val favoriteCountText = when (favoriteCount) {
                            in 0..1 -> "$favoriteCount вакансия"
                            in 2..100 -> "$favoriteCount вакансии"
                            else -> "Нет вакансии"
                        }
                        Text(
                            favoriteCountText, fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                    items(favoriteVacancies) { vacancy ->
                        VacancyCard(
                            vacancy = vacancy,
                            onCardClick = { navController.navigate("vacancy/${vacancy.id}") },
                            viewModel = viewModel,
                            isRemovalOnly = true
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
                experience = Experience(previewText = "Опыт от 1 до 3 лет", text = "1–3 года"),
                publishedDate = "2024-02-20",
                isFavorite = true,
                lookingNumber = 5,
                schedules = listOf("полная занятость", "удаленная работа"),
                appliedNumber = 0,
                salary = Salary(short = "20 - 50", full = "от 20 000 до 50 000 ₽ на руки"),
                description = "null",
                responsibilities = "null",
                questions = listOf("null")
            ),
            Vacancy(
                id = "2",
                title = "Web Developer",
                company = "Aston",
                address = Address(town = "Казань"),
                experience = Experience(previewText = "Опыт от 3 до 6 лет", text = ""),
                publishedDate = "2024-03-01",
                isFavorite = true,
                lookingNumber = 2,
                schedules = listOf("полная занятость", "удаленная работа"),
                appliedNumber = 0,
                salary = Salary(short = "20 - 50", full = "от 20 000 до 50 000 ₽ на руки"),
                description = "null",
                responsibilities = "null",
                questions = listOf("null")
            )
        )
        FavoritesScreen(
            navController = rememberNavController(),
        )
    }
}