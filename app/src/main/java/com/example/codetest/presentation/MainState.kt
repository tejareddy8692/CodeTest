package com.example.codetest.presentation

import com.example.codetest.domain.model.Animal

sealed class MainState {
    data object Loading : MainState()
    data class Animals(val list: List<Animal>) : MainState()
    data class Error(val msg: String) : MainState()
}
