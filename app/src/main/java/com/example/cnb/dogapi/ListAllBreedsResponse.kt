package com.example.cnb.dogapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListAllBreedsResponse(
    @Json(name = "message") val dogBreedsMap: Map<String, List<String>>,
    @Json(name = "status") val status: String
)
