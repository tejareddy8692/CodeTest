package com.example.codetest.presentation

import com.example.codetest.domain.model.Animal

sealed class MainIntent {
    data class AnimalClicked(val animal: Animal) : MainIntent()
}