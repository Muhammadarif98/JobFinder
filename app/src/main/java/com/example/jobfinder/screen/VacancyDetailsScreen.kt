package com.example.jobfinder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Salary
import com.example.domain.model.Vacancy
import com.example.jobfinder.component.BottomMenu
import com.example.jobfinder.component.CompanyAddressWithMap
import com.example.jobfinder.ui.theme.JobFinderTheme
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun VacancyDetailsScreen(
    navController: NavController,
    vacancyId: String,
    favoriteViewModel: FavoritesViewModel = koinViewModel()
) {
    // Получаем список избранных вакансий
    val favoriteVacancies by favoriteViewModel.favoriteVacancies.collectAsState()

    // Количество избранных вакансий
    val favoriteCount = favoriteVacancies.size
    Scaffold(
        bottomBar = {
            BottomMenu(
                navController = navController,
                favoriteCount = favoriteCount // Передаем количество избранных
            )}
    ) { paddingValues ->
        val viewModel = koinViewModel<HomeViewModel>()
        val vacancy by viewModel.getVacancyById(vacancyId).collectAsState(initial = null)



        Column(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            vacancy?.let {
                // Заголовок вакансии
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Компания и адрес
                Text(
                    text = "${it.company}, ${it.address.town}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Опыт работы
                Text(
                    text = it.experience.previewText,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Количество просматривающих и откликов
                Row() {

                    Column(
                        modifier =
                        Modifier
                            .weight(1f)
                            .background(color = Color(0xFF4CB24E), shape = RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        // Количество откликов
                        it.appliedNumber?.let { applied ->
                            Text(
                                text = "$applied человек уже откликнулись",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier =
                        Modifier
                            .weight(1f)
                            .background(color = Color(0xFF4CB24E), shape = RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        // Количество просматривающих
                        it.lookingNumber?.let { looking ->
                            val text = when (looking) {
                                in 1..2 -> "Сейчас просматривает $looking человек"
                                in 2..4 -> "Сейчас просматривает $looking человека"
                                in 4..100 -> "Сейчас просматривает $looking людей"
                                else -> "Сейчас просматривает 0 людей"
                            }
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFFFFFFF),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                CompanyAddressWithMap(it)
                Spacer(modifier = Modifier.height(16.dp))

                // Описание вакансии
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Обязанности
                Text(
                    text = "Обязанности:",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = it.responsibilities,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Вопросы работодателю
                if (it.questions.isNotEmpty()) {
                    Text(
                        text = "Задайте вопрос работодателю",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    it.questions.forEach { question ->
                        question?.let {
                            Text(
                                text = "- $it",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }

                Button(
                    onClick = { println("Кнопка нажата") }, modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), colors =
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF4CB24E))
                ) {

                    Text(text = "Откликнуться", color = Color.White)

                }
            } ?: run {
                Text(text = "Вакансия не найдена")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VacancyDetailsScreenPreview() {
    JobFinderTheme {
        // Создаем заглушку для Vacancy
        val mockVacancy = Vacancy(
            id = "123",
            lookingNumber = 2,
            title = "UI/UX Designer",
            address = Address(town = "Минск", street = "улица Бирюзова", house = "4/5"),
            company = "Мобирикс",
            experience = Experience(
                previewText = "Требуемый опыт: от 1 года до 3 лет",
                text = "Опыт работы в UI/UX дизайне от 1 года"
            ),
            publishedDate = "2023-10-01",
            isFavorite = false,
            salary = Salary(short = "Уровень дохода не указан", full = "Зарплата не указана"),
            schedules = listOf("Полная занятость, полный день"),
            appliedNumber = 147,
            description = "MOBYRIX - динамично развивающаяся продуктовая IT-компания, специализирующаяся на разработке мобильных приложений для iOS и Android. Наша команда работает над собственными продуктами в разнообразных сферах: от утилит до развлечений и бизнес-приложений.",
            responsibilities = "- Проектировать пользовательский опыт, проводить UX исследования;\n" +
                    "- Разрабатывать адаптивный дизайн интерфейса для мобильных приложений;\n" +
                    "- Разрабатывать быстрые прототипы для тестирования идеи дизайна и их последующая интеграция на основе обратной связи от команды и пользователей;\n" +
                    "- Взаимодействовать с командой разработчиков для обеспечения точной реализации ваших дизайнов;\n" +
                    "- Анализ пользовательского опыта и адаптация под тренды.",
            questions = listOf(
                "Где располагается место работы?",
                "Какой график работы?",
                "Вакансия открыта?",
                "Какая оплата труда?"
            )
        )

        // Для превью передаем заглушку NavController и mockVacancy
        VacancyDetailsScreen(
            navController = rememberNavController(),
            vacancyId = "mockVacancy",
        )
    }
}