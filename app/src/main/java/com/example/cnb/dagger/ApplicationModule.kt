package com.example.cnb.dagger

import android.app.Application
import com.example.cnb.DogBreedApplication
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApplicationModule(var app: DogBreedApplication) {
    @Provides
    fun provideApplication(): Application {
        return app
    }
}