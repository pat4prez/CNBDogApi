package com.example.cnb.dogapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogBreedImageResponse(
    @Json(name = "message") val dogImage: String
)
