package com.example.codetest.presentation

import com.example.codetest.domain.model.Animal
import com.example.codetest.domain.usecase.GetAnimalsUseCase
import com.example.codetest.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.IOException

class MainViewModelTest {

    private val animalsUseCase = mockk<GetAnimalsUseCase>()
    private val animal = mockk<Animal>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when success to get animal list`() = runTest {
        val animals = listOf(animal)
        coEvery { animalsUseCase() } returns animals

        val mainViewModel = MainViewModel(animalsUseCase)

        expectThat(mainViewModel.state.value).isEqualTo(MainState.Animals(animals))
    }

    @Test
    fun `when fail to get animal list`() = runTest {
        val msg = "msg"
        coEvery { animalsUseCase() } throws  IOException(msg)

        val mainViewModel = MainViewModel(animalsUseCase)

        expectThat(mainViewModel.state.value).isEqualTo(MainState.Error(msg))
    }

}