package com.example.cnb.dogbreeddetails

import com.example.cnb.repository.DogBreedRepo
import javax.inject.Inject

class DogBreedDetailsPresenter @Inject constructor(private val dogBreedRepo: DogBreedRepo) {

    fun loadImageURL(breed: String, subBreed: String?, dogBreedDetailsListener: DogBreedRepo.DogBreedDetailsListener) {
        dogBreedRepo.loadDogBreedImage(breed, subBreed, dogBreedDetailsListener)
    }
}
