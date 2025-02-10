package com.example.jobfinder.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jobfinder.ui.theme.JobFinderTheme

@Composable
fun VacancyDetailsScreen(navController: NavController, vacancyId: String?) {
    Scaffold(
        bottomBar = { com.example.jobfinder.component.BottomMenu(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Детали вакансии: $vacancyId")
            // Здесь будет детальная информация о вакансии
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VacancyDetailsScreenPreview() {
    JobFinderTheme {
        // Для превью передаем заглушку NavController
        VacancyDetailsScreen(
            navController = rememberNavController(),
            vacancyId = "123"
        )
    }
}