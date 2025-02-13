package com.example.jobfinder.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfinder.component.BottomMenu
import com.example.jobfinder.component.CountVacancies
import com.example.jobfinder.component.OfferCard
import com.example.jobfinder.component.TopBar
import com.example.jobfinder.component.VacancyCard
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    favoriteViewModel: FavoritesViewModel = koinViewModel()
) {
    val data by viewModel.data.collectAsState()
    var showAllVacancies by remember { mutableStateOf(false) }
    // Получаем список избранных вакансий
    val favoriteVacancies by favoriteViewModel.favoriteVacancies.collectAsState()

    // Количество избранных вакансий
    val favoriteCount = favoriteVacancies.size
    Scaffold(

        topBar = {
            TopBar(
                showBackButton = showAllVacancies,
                onBackClick = { showAllVacancies = false },
                showVacanciesText = showAllVacancies
            )
        },
        bottomBar = {
            BottomMenu(
                navController = navController,
                favoriteCount = favoriteCount
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            data?.let { apiResponse ->
                CountVacancies(
                    showBackButton = showAllVacancies,
                    vacanciesCount = data?.vacancies?.size ?: 0,
                )
                // Отображение рекомендаций
                LazyRow (modifier = Modifier.padding(8.dp),){
                    items(apiResponse.offers) { offer ->
                        OfferCard(offer = offer)
                    }
                }

                // Отображение вакансий
                val vacancies = if (showAllVacancies) {
                    apiResponse.vacancies
                } else {
                    apiResponse.vacancies.take(3)
                }

                LazyColumn(modifier = Modifier.padding(8.dp),) {
                    item {
                        Text(
                            "Вакансии для вас",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(vacancies) { vacancy ->
                        VacancyCard(
                            vacancy = vacancy.copy(isFavorite = favoriteViewModel.isFavorite(vacancy.id)),
                            onCardClick = { navController.navigate("vacancy/${vacancy.id}") },
                            viewModel = favoriteViewModel,
                            isRemovalOnly = false
                        )
                    }


                    // Кнопка "Еще {число вакансий} вакансии"
                    if (!showAllVacancies && apiResponse.vacancies.size > 3) {
                        item {
                            val remainingVacancies = apiResponse.vacancies.size - 3
                            val text = when (remainingVacancies) {
                                1 -> "Еще $remainingVacancies вакансия"
                                in 2..4 -> "Еще $remainingVacancies вакансии"
                                else -> "Еще $remainingVacancies вакансий"
                            }

                            Button(
                                onClick = { showAllVacancies = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2B7EFE),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = text,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
