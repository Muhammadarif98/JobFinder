package com.example.jobfinder.di

import android.content.Context
import com.example.data.sourse.LocalDataSource
import com.example.jobfinder.viewmodel.FavoritesViewModel
import com.example.jobfinder.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LocalDataSource(androidContext()) }
    viewModel { HomeViewModel(get()) } // Koin автоматически внедряет LocalDataSource
    viewModel { FavoritesViewModel(androidContext()) }
}
