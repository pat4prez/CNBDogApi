package com.example.cnb.dogapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogBreedApiInterface {

    @GET("breeds/list/all")
    fun getListAllBreeds(): Call<ListAllBreedsResponse>

    @GET("breed/{breed}/images/random")
    fun getRandomDogBreedImage(@Path("breed") breed: String): Call<DogBreedImageResponse>

    @GET("breed/{breed}/{subbreed}/images/random")
    fun getRandomSubBreedImage(@Path("breed") breed: String, @Path("subbreed") subbreed: String): Call<SubBreedImageResponse>

}