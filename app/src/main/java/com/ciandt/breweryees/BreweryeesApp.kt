package com.ciandt.breweryees

import android.app.Application
import com.ciandt.breweryees.di.networkingModule
import com.ciandt.breweryees.di.repositoriesModule
import com.ciandt.breweryees.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BreweryeesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BreweryeesApp)
            modules(networkingModule, repositoriesModule, viewModelModule)
        }
    }
}