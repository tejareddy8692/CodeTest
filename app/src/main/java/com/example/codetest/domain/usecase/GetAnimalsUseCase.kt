package com.example.codetest.domain.usecase

import com.example.codetest.domain.AnimalRepository
import javax.inject.Inject

class GetAnimalsUseCase @Inject constructor(private val animalRepository: AnimalRepository) {
    suspend operator fun invoke() = animalRepository.getAnimals()
}