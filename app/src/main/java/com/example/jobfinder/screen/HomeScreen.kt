package com.example.jobfinder.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jobfinder.component.BottomMenu
import com.example.jobfinder.component.OfferCard
import com.example.jobfinder.component.VacancyCard
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel, // Koin inject
    favoriteViewModel: FavoritesViewModel = koinViewModel()
) {
    val data by viewModel.data.collectAsState()

    Scaffold(
        bottomBar = { BottomMenu(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            data?.let { apiResponse ->
                // Отображение рекомендаций
                LazyRow {
                    items(apiResponse.offers) { offer ->
                        OfferCard(offer = offer)
                    }
                }

                // Отображение вакансий
                LazyColumn {
                    items(apiResponse.vacancies) { vacancy ->
                        VacancyCard(
                            vacancy = vacancy,
                            onCardClick = {
                                navController.navigate("vacancy/${vacancy.id}")
                            },
                            onFavoriteClick = {
                                favoriteViewModel.addToFavorites(vacancy = vacancy)
                            }
                        )
                    }
                }
            }
        }
    }
}