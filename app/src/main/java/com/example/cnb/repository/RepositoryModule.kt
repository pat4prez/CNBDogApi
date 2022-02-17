package com.example.cnb.repository

import com.example.cnb.dagger.ApplicationScope
import com.example.cnb.dogapi.DogBreedApiInterface
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideDogBreedRepository(dogBreedApiInterface: DogBreedApiInterface): DogBreedRepo {
        return DogBreedRepo(dogBreedApiInterface)
    }
}