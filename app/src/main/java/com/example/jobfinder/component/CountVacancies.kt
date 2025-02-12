package com.example.jobfinder.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.ui.theme.LightBlue
import com.example.jobfinder.ui.theme.Purple40

@Composable
fun CountVacancies(
    showBackButton: Boolean,
    vacanciesCount: Int,
) {
    if (showBackButton) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "$vacanciesCount ${getVacanciesText(vacanciesCount)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "По соответствию",
                style = MaterialTheme.typography.bodyMedium,
                color = LightBlue
            )
            Icon(
                painterResource(id = R.drawable.ic_soot), // Иконка поиска внутри TextField
                contentDescription = "Поиск",
                tint = Color.Unspecified
            )
        }
    }
}
// Функция для склонения слова "вакансия"
private fun getVacanciesText(count: Int): String {
    return when (count) {
        1 -> "вакансия";
        in 2..4 -> "вакансии";
        else -> "вакансий"
    }
}
@Preview(showSystemUi = true)
@Composable
fun CountVacanciesPreview() {
    JobFinderTheme {
        Column {
            CountVacancies(
                showBackButton = true && true,//Показываем кнопку назад для примера
                vacanciesCount = 1,//Примерное количество вакансий
            )
        }
    }
}