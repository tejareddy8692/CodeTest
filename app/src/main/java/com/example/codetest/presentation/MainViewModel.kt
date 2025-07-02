package com.example.codetest.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codetest.domain.usecase.GetAnimalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAnimalsUseCase: GetAnimalsUseCase) :
    ViewModel() {

    var state = mutableStateOf<MainState>(MainState.Loading)
        private set

    init {
        getAnimalsFromRepo()
    }

    private fun getAnimalsFromRepo() {
        viewModelScope.launch {
            state.value = MainState.Loading
            try {
                state.value = MainState.Animals(getAnimalsUseCase())
            } catch (exception: Exception) {
                state.value = MainState.Error(exception.localizedMessage.toString())
            }
        }
    }
}