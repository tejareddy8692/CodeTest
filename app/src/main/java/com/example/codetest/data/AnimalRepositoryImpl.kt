package com.example.codetest.data

import com.example.codetest.data.mapper.AnimalMapper
import com.example.codetest.domain.AnimalRepository
import com.example.codetest.domain.model.Animal
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val animalApi: AnimalApi,
    private val mapper: AnimalMapper
) : AnimalRepository {
    override suspend fun getAnimals(): List<Animal> = animalApi.getAnimals().map { animalDto ->
        mapper.mapAnimal(animalDto)
    }
}