package com.example.cnb.repository

import com.example.cnb.dagger.ActivityScope
import com.example.cnb.dogapi.DogBreedApiInterface
import com.example.cnb.dogapi.DogBreedImageResponse
import com.example.cnb.dogapi.ListAllBreedsResponse
import com.example.cnb.dogapi.SubBreedImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class DogBreedRepo @Inject constructor(private val dogBreedApiInterface: DogBreedApiInterface) {

    private var dogBreedMap: Map<String, List<String>> = mapOf()

    fun loadDogList(dogListListener: DogListListener) {
        if (dogBreedMap.isNullOrEmpty()) {
            makeListAllBreedsResponse(dogListListener)
        } else {
            dogListListener.onDogListLoaded(dogBreedMap)
        }
    }

    private fun makeListAllBreedsResponse(dogListListener: DogListListener) {
        dogBreedApiInterface
            .getListAllBreeds()
            .enqueue(object : Callback<ListAllBreedsResponse> {
                override fun onResponse(call: Call<ListAllBreedsResponse>, response: Response<ListAllBreedsResponse>) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        dogBreedMap = responseBody.dogBreedsMap
                        dogListListener.onDogListLoaded(dogBreedMap)
                    } else {
                        dogListListener.onFailure()
                    }
                }

                override fun onFailure(call: Call<ListAllBreedsResponse>, t: Throwable) {
                    dogListListener.onFailure()
                }
            })
    }

    fun loadDogBreedImage(breed: String, subBreed: String?, dogBreedDetailsListener: DogBreedDetailsListener) {
        if (!subBreed.isNullOrEmpty()) {
            loadSubBreedImage(breed, subBreed, dogBreedDetailsListener)
        } else {
            loadMainBreedImage(breed, dogBreedDetailsListener)
        }
    }

    private fun loadMainBreedImage(breed: String, dogBreedDetailsListener: DogBreedDetailsListener) {
        dogBreedApiInterface
            .getRandomDogBreedImage(breed)
            .enqueue(object : Callback<DogBreedImageResponse> {
                override fun onResponse(call: Call<DogBreedImageResponse>, response: Response<DogBreedImageResponse>) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        dogBreedDetailsListener.onImageUrlLoaded(responseBody.dogImage)
                    } else {
                        dogBreedDetailsListener.onFailure()
                    }
                }

                override fun onFailure(call: Call<DogBreedImageResponse>, t: Throwable) {
                    dogBreedDetailsListener.onFailure()
                }
            })
    }

    private fun loadSubBreedImage(breed: String, subBreed: String, dogBreedDetailsListener: DogBreedDetailsListener) {
        dogBreedApiInterface
            .getRandomSubBreedImage(breed, subBreed)
            .enqueue(object : Callback<SubBreedImageResponse> {
                override fun onResponse(call: Call<SubBreedImageResponse>, response: Response<SubBreedImageResponse>) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        dogBreedDetailsListener.onImageUrlLoaded(responseBody.dogImage)
                    } else {
                        dogBreedDetailsListener.onFailure()
                    }
                }

                override fun onFailure(call: Call<SubBreedImageResponse>, t: Throwable) {
                    dogBreedDetailsListener.onFailure()
                }
            })
    }

    interface DogListListener {
        fun onDogListLoaded(dogBreedMap: Map<String, List<String>>)
        fun onFailure()
    }

    interface DogBreedDetailsListener {
        fun onImageUrlLoaded(url: String)
        fun onFailure()
    }
}