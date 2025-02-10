package com.example.data.sourse

import android.content.Context
import com.example.data.R
import com.example.domain.model.ApiResponse
import com.example.domain.model.Vacancy
import com.google.gson.Gson


class LocalDataSource(private val context: Context) {

    fun getData(): ApiResponse {
        // Ваш существующий метод для получения данных
        val jsonString = context.resources.openRawResource(R.raw.mock)
            .bufferedReader()
            .use { it.readText() }
        return Gson().fromJson(jsonString, ApiResponse::class.java)
    }

    suspend fun getFavorites(): List<Vacancy> {
        // Реализация получения списка избранных вакансий из локального хранилища.
        // Для примера:

        // Если вы используете Room или SharedPreferences для хранения данных,
        // то здесь будет код для чтения этих данных.

        return listOf()  // Возвращает пустой список пока не реализована логика.
    }
}
