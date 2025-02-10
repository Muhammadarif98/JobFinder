package com.example.jobfinder.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Vacancy
import com.example.jobfinder.R

@Composable
fun CompanyAddressWithMap(vacancy: Vacancy) {
    Box(
        modifier = Modifier
            .height(134.dp)
            .padding(bottom = 8.dp)
    ) {
        // Статическое изображение карты
        Image(
            painter = painterResource(id = R.drawable.static_map), // Замените на ваше изображение карты
            contentDescription = "Карта",
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)) // Закругление углов
        )

        // Название компании и адрес
        Text(
            text = "${vacancy.company}, ${vacancy.address.town}",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart)
        )
    }
}