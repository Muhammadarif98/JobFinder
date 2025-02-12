package com.example.jobfinder.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobfinder.MainActivity
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme

@Composable
fun TopBar(
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    showVacanciesText: Boolean = false // Новый параметр для контроля видимости текста вакансий
) {
    Row(modifier = Modifier.padding(16.dp)) {


        TextField(
            value = "",

            onValueChange = { /* Обработка изменения текста */ },
            modifier = Modifier.padding(horizontal = 8.dp),
            leadingIcon = {
                if (showBackButton && showVacanciesText) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back), // Замените на вашу иконку
                            contentDescription = "Назад"
                        )
                    }
                } else {
                    Icon(
                        painterResource(id = R.drawable.ic_search_grey), // Иконка поиска внутри TextField
                        contentDescription = "Поиск"
                    )
                }
            },
            placeholder = {
                Text(
                    text = "Должность, ключевые слова",
                    color = Color.LightGray
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )


        IconButton(
            onClick = {/*Обработка клика на фильтр*/ },
            colors = IconButtonColors(
                containerColor = Color.Gray,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),//Замените на вашу иконку
                contentDescription = "Фильтр"
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun TopBarPreview() {
    JobFinderTheme {
        Column {
            TopBar(
                showBackButton = true && true,//Показываем кнопку назад для примера
                onBackClick = {/*Обработка клика*/ },
                showVacanciesText = true//Показываем текст количества вакансий
            )

            Spacer(modifier = Modifier.height(16.dp))

            TopBar(
                showBackButton = false && false,//Не показываем кнопку назад для примера
                onBackClick = {/*Обработка клика*/ },
            )
        }
    }
}

