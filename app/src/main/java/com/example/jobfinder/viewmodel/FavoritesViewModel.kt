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
            _favoriteVacancies.value += vacancy
        }
    }

    fun removeFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            _favoriteVacancies.value -= vacancy
        }
    }
}