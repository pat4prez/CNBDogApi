package com.example.cnb.dogbreedlist

import com.example.cnb.repository.DogBreedRepo
import javax.inject.Inject

class DogBreedPresenter @Inject constructor(private val dogBreedRepo: DogBreedRepo) {

    fun getDogBreedList(dogListListener: DogBreedRepo.DogListListener) {
        dogBreedRepo.loadDogList(dogListListener)
    }

    fun createDogBreedList(dogBreedMap: Map<String, List<String>>): MutableList<Pair<String, String>> {
        val dogFullBreedMap: MutableList<Pair<String, String>> = mutableListOf()

        for (dogBreed in dogBreedMap) {
            val dogTitle = dogBreed.key
            if (dogBreed.value.isEmpty()) {
                dogFullBreedMap.add(Pair(dogTitle, ""))
            } else {
                for (subBreed in dogBreed.value) {
                    dogFullBreedMap.add(Pair(dogTitle, subBreed))

                }
            }
        }
        return dogFullBreedMap
    }

}
