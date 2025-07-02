package com.example.codetest.data.mapper

import com.example.codetest.data.dto.AnimalDto
import com.example.codetest.domain.model.Animal
import javax.inject.Inject

class AnimalMapper @Inject constructor() {
    fun mapAnimal(animalDto: AnimalDto): Animal =
        Animal(animalDto.name, animalDto.location, animalDto.image)
}