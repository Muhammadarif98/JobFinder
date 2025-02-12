package com.example.jobfinder.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Button
import com.example.domain.model.Offer
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme


@Composable
fun OfferCard(offer: Offer) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .width(150.dp)
            .height(170.dp)
            .padding(8.dp)
            .clickable { /* Обработка клика */ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Отображаем иконку только если id не null
            offer.id?.let { id ->
                val iconResId = when (id) {
                    "near_vacancies" -> R.drawable.ic_near_vacancies
                    "level_up_resume" -> R.drawable.ic_level_up_resume
                    "temporary_job" -> R.drawable.ic_temporary_job
                    else -> null // Не отображаем иконку, если id не совпадает
                }

                iconResId?.let { resId ->
                    Icon(
                        painter = painterResource(resId),
                        contentDescription = "Offer Icon",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Text(text = offer.title, style = MaterialTheme.typography.titleMedium)
            offer.button?.let { button ->
                Text(text = button.text, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OfferCardPreview() {
    OfferCard(
        offer = Offer(
            id = "temporary_job",
            title = "Временная работа или подработка",
            link = "https://hh.ru/",
            button = Button(text = "text")
        )
    )
}