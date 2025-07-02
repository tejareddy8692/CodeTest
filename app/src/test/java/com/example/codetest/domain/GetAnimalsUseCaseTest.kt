package com.example.codetest.domain

import com.example.codetest.domain.model.Animal
import com.example.codetest.domain.usecase.GetAnimalsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isNotNull

class GetAnimalsUseCaseTest {

    private lateinit var getAnimalsUseCase: GetAnimalsUseCase
    private val animalRepository = mockk<AnimalRepository>()
    private val animal = mockk<Animal>()

    @Before
    fun setup(){
        getAnimalsUseCase = GetAnimalsUseCase(animalRepository)
    }

    @Test
    fun `repository returns list of animals`() = runTest {
        coEvery { animalRepository.getAnimals() } returns listOf(animal)

        expectThat(getAnimalsUseCase()).isNotNull()
    }
}