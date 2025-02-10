package com.example.jobfinder.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Vacancy
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme

@Composable
fun VacancyCard(
    vacancy: Vacancy,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = vacancy.title, style = MaterialTheme.typography.titleMedium)

                    Text(
                        text = vacancy.company,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    Text(
                        text = vacancy.address.town,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 6.dp)
                    )

                    Text(
                        text = vacancy.experience.previewText,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "Опубликовано ${vacancy.publishedDate}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )


                }


                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = "Избранное"
                    )
                }// Padding для красоты

            }

        }
        Button(
            onClick = { println("Кнопка нажата") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CB24E))
        ) {
            Text(text = "Откликнуться", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VacancyCardPreview() {
    JobFinderTheme {
        VacancyCard(
            vacancy = Vacancy(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "UI/UX дизайнер",
                address = Address(
                    town = "Минск",
                    street = "улица Бирюзова",
                    house = "4/5"
                ),
                company = "Мобирикс",
                experience = Experience(
                    previewText = "Опыт от 1 до 3 лет",
                    text = "1–3 года"
                ),
                publishedDate = "2024-02-20",
                isFavorite = false,
            ),
            onCardClick = { /*TODO*/ },
            onFavoriteClick = {}
        )
    }
}
