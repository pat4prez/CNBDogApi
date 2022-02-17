package com.example.cnb.repository.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DogBreed(var title: String, val subBreeds: List<String>)
