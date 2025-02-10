package com.example.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val _favoriteVacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val favoriteVacancies: StateFlow<List<Vacancy>> = _favoriteVacancies

    fun addToFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            val updatedVacancy = vacancy.copy(isFavorite = true)
            _favoriteVacancies.value += updatedVacancy
        }
    }

    fun updateVacancyState(vacancyId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            val updatedList = _favoriteVacancies.value.map { vacancy ->
                if (vacancy.id == vacancyId) {
                    vacancy.copy(isFavorite = isFavorite)
                } else {
                    vacancy
                }
            }
            _favoriteVacancies.value = updatedList
        }
    }
    fun removeFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            val updatedList = _favoriteVacancies.value.filter { it.id != vacancy.id }
            val finalList = updatedList.map { v ->
                if (v.id == vacancy.id && v in _favoriteVacancies.value) {
                    v.copy(isFavorite = false)
                } else v
            }
            _favoriteVacancies.value = finalList
        }
    }

}