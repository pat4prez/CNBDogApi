package com.example.cnb.dagger

import com.example.cnb.dogbreeddetails.DogBreedDetailsActivity
import com.example.cnb.dogbreedlist.DogBreedActivity
import com.example.cnb.repository.RepositoryModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, PresenterModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun inject(dogBreedActivity: DogBreedActivity)
    fun inject(dogBreedDetailsActivity: DogBreedDetailsActivity)
}