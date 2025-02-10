package com.example.jobfinder.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Offer
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.JobFinderTheme



@Composable
fun OfferCard(offer: Offer) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Обработка клика */ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                painterResource(R.drawable.ic_star), // Выберите нужный иконку или используйте свой собственный drawable.
                contentDescription = "Offer Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp) // Укажите размер иконки.
            )

            Spacer(modifier = Modifier.width(8.dp))
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

}