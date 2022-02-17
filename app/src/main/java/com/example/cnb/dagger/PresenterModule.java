package com.example.cnb.dagger;

import com.example.cnb.dogbreedlist.DogBreedPresenter;
import com.example.cnb.repository.DogBreedRepo;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class PresenterModule {

    @Provides
    @ApplicationScope
    public static DogBreedPresenter getDogBreedPresenter(DogBreedRepo dogBreedRepo) {
        return new DogBreedPresenter(dogBreedRepo);
    }


}