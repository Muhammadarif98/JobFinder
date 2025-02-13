package com.example.jobfinder.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Salary
import com.example.domain.model.Vacancy
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VacancyCard(
    vacancy: Vacancy,
    onCardClick: () -> Unit,
    viewModel: FavoritesViewModel = koinViewModel(),
    isRemovalOnly: Boolean = false
) {
    val isFavorite by remember { mutableStateOf(viewModel.isFavorite(vacancy.id)) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() }
    ) {
        Column(Modifier.padding(16.dp)) {

            vacancy.lookingNumber?.let { looking ->
                val text = when {
                    looking % 10 == 1 && looking % 100 != 11 -> "Сейчас просматривает $looking человек"
                    looking % 10 in 2..4 && looking % 100 !in 12..14 -> "Сейчас просматривает $looking человека"
                    else -> "Сейчас просматривает $looking людей"
                }
                Row(verticalAlignment = CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = text,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4CB24E),
                    )
                    Spacer(modifier = Modifier.size(8.dp))

                    IconButton(onClick = {
                        if (isRemovalOnly) {
                            // Только удаление (для FavoritesScreen)
                            viewModel.removeFromFavorites(vacancy)
                        } else {
                            // Добавление или удаление (для HomeScreen)
                            if (isFavorite) {
                                viewModel.removeFromFavorites(vacancy)
                            } else {
                                viewModel.addToFavorites(vacancy)
                            }
                        }
                    }) {
                        Icon(
                            painter = if (isFavorite) painterResource(id = R.drawable.ic_favorite_blue)
                            else painterResource(id = R.drawable.ic_favorite),
                            contentDescription = "Избранное",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            Text(
                text = vacancy.title,
                style = MaterialTheme.typography.titleLarge
            )
            vacancy.salary.short?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Row(verticalAlignment = CenterVertically) {
                Text(
                    text = vacancy.address.town,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_hardcod),
                    contentDescription = "Избранное",
                    tint = Color.Unspecified
                )
            }


            Text(
                text = vacancy.company,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )

            Row(verticalAlignment = CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exp),
                    contentDescription = "Избранное",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = vacancy.experience.previewText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }


            Text(
                text = "Опубликовано ${vacancy.publishedDate}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Row(
                modifier =
                Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier =
                    Modifier.weight(1f)
                ) {
                    Spacer(
                        modifier =
                        Modifier.height(24.dp)
                    )
                }


            }

            Button(
                onClick = { println("Кнопка нажата") },
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                    Color(0xFF4CB24E)
                )
            ) {

                Text(
                    text = "Откликнуться", color =
                    Color.White
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VacancyCardPreview() {
    JobFinderTheme {
        VacancyCard(
            vacancy = Vacancy(
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
                description = "description",
                responsibilities = "responsibilities",
                questions = listOf("questions,questions")
            ),
            onCardClick = {},

            )
    }
}