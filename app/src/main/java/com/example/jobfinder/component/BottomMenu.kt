package com.example.jobfinder.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfinder.R


@Composable
fun BottomMenu(navController: NavController) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_search_grey), contentDescription = "Поиск") },
                label = { Text("Поиск") },
                selected = false,
                onClick = {
                    navController.navigate("home")
                }
            )
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_favorite), contentDescription = "Избранное") },
                label = { Text("Избранное") },
                selected = false,
                onClick = {
                    navController.navigate("favorites")
                }
            )
            // Добавьте другие пункты меню
        }
    }
}
