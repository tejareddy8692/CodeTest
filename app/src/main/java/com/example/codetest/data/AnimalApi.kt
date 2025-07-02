package com.example.codetest.data

import com.example.codetest.data.dto.AnimalDto
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals.json")
    suspend fun getAnimals(): List<AnimalDto>

}