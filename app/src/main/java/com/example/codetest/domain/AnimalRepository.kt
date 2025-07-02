package com.example.codetest.domain

import com.example.codetest.domain.model.Animal

interface AnimalRepository {
    suspend fun getAnimals():List<Animal>

}