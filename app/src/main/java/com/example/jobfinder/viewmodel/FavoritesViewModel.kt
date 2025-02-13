package com.example.jobfinder.viewmodel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.sourse.LocalDataSource
import com.example.domain.model.Address
import com.example.domain.model.Experience
import com.example.domain.model.Salary
import com.example.domain.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(context: Context) : ViewModel() {

    private val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val _favoriteVacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val favoriteVacancies: StateFlow<List<Vacancy>> = _favoriteVacancies
    private val localDataSource: LocalDataSource = LocalDataSource(context)
    init {
        loadFavorites()
    }

    // Загрузка избранных вакансий из SharedPreferences
    fun loadFavorites() {
        viewModelScope.launch {
            val favorites = sharedPreferences.all.mapNotNull { (key, value) ->
                if (value == true) {
                    localDataSource.getVacancyById(key) // Получаем реальные данные вакансии
                } else null
            }
            _favoriteVacancies.value = favorites
        }
    }


    fun toggleFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            if (vacancy.isFavorite) {
                removeFromFavorites(vacancy)
            } else {
                addToFavorites(vacancy)
            }
        }
    }

    fun addToFavorites(vacancy: Vacancy) {
        sharedPreferences.edit().putBoolean(vacancy.id, true).apply()
        _favoriteVacancies.value += vacancy.copy(isFavorite = true)
    }

    fun removeFromFavorites(vacancy: Vacancy) {
        sharedPreferences.edit().remove(vacancy.id).apply()
        _favoriteVacancies.value = _favoriteVacancies.value.filter { it.id != vacancy.id }
    }

    // Проверка, добавлена ли вакансия в избранное
    fun isFavorite(vacancyId: String): Boolean {
        return sharedPreferences.getBoolean(vacancyId, false)
    }
}