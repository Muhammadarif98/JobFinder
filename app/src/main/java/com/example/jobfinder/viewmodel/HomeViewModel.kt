package com.example.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.sourse.LocalDataSource
import com.example.domain.model.ApiResponse
import com.example.domain.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val localDataSource: LocalDataSource) : ViewModel() {

    private val _data = MutableStateFlow<ApiResponse?>(null)
    val data: StateFlow<ApiResponse?> = _data

    private val _favoriteVacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val favoriteVacancies: StateFlow<List<Vacancy>> = _favoriteVacancies

    init {
        loadData()

    }

    private fun loadData() {
        viewModelScope.launch {
            _data.value = localDataSource.getData()
        }
    }
}
