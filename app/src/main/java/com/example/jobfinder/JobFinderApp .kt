package com.example.jobfinder

import android.app.Application
import com.example.jobfinder.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JobFinderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JobFinderApp)
            modules(appModule)
        }
    }
}