package com.example.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.sourse.LocalDataSource
import com.example.domain.model.ApiResponse
import com.example.domain.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class HomeViewModel(private val localDataSource: LocalDataSource) : ViewModel() {
    private val _data = MutableStateFlow<ApiResponse?>(null)
    val data: StateFlow<ApiResponse?> = _data

    init {
        loadData()
    }
    fun getVacancyById(id: String): Flow<Vacancy?> = flow {
        emit(localDataSource.getVacancyById(id))
    }
    private fun loadData() {
        viewModelScope.launch {
            val apiResponse = localDataSource.getData()

            // Обновите состояние isFavorite для каждой вакансии
            val updatedVacancies = apiResponse?.vacancies?.map { vacancy ->
                vacancy.copy(
                    isFavorite = vacancy.isFavorite,
                    description = vacancy.description ?: "", // Установите пустую строку или другое значение по умолчанию.
                    appliedNumber = vacancy.appliedNumber ?: 0,
                    lookingNumber = vacancy.lookingNumber ?: 0
                )
            }

            val updatedApiResponse = apiResponse?.copy(vacancies = updatedVacancies ?: emptyList())

            _data.value = updatedApiResponse
        }
    }

}

