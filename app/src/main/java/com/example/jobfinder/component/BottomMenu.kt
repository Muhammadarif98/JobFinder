package com.example.jobfinder.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfinder.R

@Composable
fun BottomMenu(navController: NavController, favoriteCount: Int) {
    // Получаем текущий маршрут навигации
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Определяем выбранный пункт меню на основе текущего маршрута
    val selectedItem = when (currentRoute) {
        "home" -> "home"
        "favorites" -> "favorites"
        "responses" -> "responses"
        "messages" -> "messages"
        "profile" -> "profile"
        else -> "home" // По умолчанию
    }

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // Поиск
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedItem == "home") R.drawable.ic_search_blue else R.drawable.ic_search_grey
                        ),
                        contentDescription = "Поиск",
                        tint = Color.Unspecified
                    )
                },
                label = { Text("Поиск", fontSize = 10.sp) },
                selected = selectedItem == "home",
                onClick = {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified, // Цвет иконки выбранного элемента
                    selectedTextColor = Color.Unspecified, // Цвет текста выбранного элемента
                    indicatorColor = Color.Transparent // Убираем серый круг
                )
            )

            // Избранное
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (favoriteCount > 0) {
                                Badge {
                                    Text(text = favoriteCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (selectedItem == "favorites") R.drawable.ic_favorite_blue else R.drawable.ic_favorite
                            ),
                            contentDescription = "Избранное",
                            tint = Color.Unspecified
                        )
                    }
                },
                label = { Text("Избранное", fontSize = 10.sp) },
                selected = selectedItem == "favorites",
                onClick = {
                    navController.navigate("favorites") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    indicatorColor = Color.Transparent // Убираем серый круг
                )
            )

            // Отклики
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedItem == "responses") R.drawable.ic_responses_blue else R.drawable.ic_responses_grey
                        ),
                        contentDescription = "Отклики",
                        tint = Color.Unspecified
                    )
                },
                label = { Text("Отклики", fontSize = 10.sp) },
                selected = selectedItem == "responses",
                onClick = {
                    navController.navigate("responses") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    indicatorColor = Color.Transparent // Убираем серый круг
                )
            )

            // Сообщения
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedItem == "messages") R.drawable.ic_messages_blue else R.drawable.ic_messages_grey
                        ),
                        contentDescription = "Сообщения",
                        tint = Color.Unspecified
                    )
                },
                label = { Text("Сообщения", fontSize = 10.sp) },
                selected = selectedItem == "messages",
                onClick = {
                    navController.navigate("messages") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    indicatorColor = Color.Transparent // Убираем серый круг
                )
            )

            // Профиль
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedItem == "profile") R.drawable.ic_profile_blue else R.drawable.ic_profile_grey
                        ),
                        contentDescription = "Профиль",
                        tint = Color.Unspecified
                    )
                },
                label = { Text("Профиль", fontSize = 10.sp) },
                selected = selectedItem == "profile",
                onClick = {
                    navController.navigate("profile") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    indicatorColor = Color.Transparent // Убираем серый круг
                )
            )
        }
    }
}
