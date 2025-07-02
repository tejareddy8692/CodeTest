package com.example.codetest.data


import com.example.codetest.data.dto.AnimalDto
import com.example.codetest.data.mapper.AnimalMapper
import com.example.codetest.domain.model.Animal
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isNotNull

class AnimalRepositoryImplTest {

    private lateinit var animalRepositoryImpl: AnimalRepositoryImpl

    @MockK
    private lateinit var animalApi: AnimalApi

    @MockK
    private lateinit var mapper: AnimalMapper

    @MockK
    lateinit var animalDto: AnimalDto

    @MockK
    lateinit var animal: Animal

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        animalRepositoryImpl = AnimalRepositoryImpl(animalApi, mapper)
    }

    @Test
    fun `when api is called return list of animals`() = runTest {
        val animalList = listOf(animalDto)
        every { animalDto.image } returns "image"
        every { animalDto.name } returns "name"
        every { animalDto.location } returns "location"
        coEvery { animalApi.getAnimals() } returns animalList
        every { mapper.mapAnimal(animalDto) } returns animal
        val result = animalRepositoryImpl.getAnimals().first()
        expectThat(result).isNotNull().isA<Animal>()
    }

}