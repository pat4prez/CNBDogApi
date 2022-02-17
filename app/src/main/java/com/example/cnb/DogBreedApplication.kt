package com.example.cnb

import android.app.Application
import com.example.cnb.dagger.ApplicationComponent
import com.example.cnb.dagger.ContextModule
import com.example.cnb.dagger.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class DogBreedApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}