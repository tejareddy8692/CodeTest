package com.example.codetest.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codetest.domain.model.Animal
import com.example.codetest.domain.usecase.GetAnimalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAnimalsUseCase: GetAnimalsUseCase) : ViewModel() {

    var state = mutableStateOf<MainState>(MainState.Loading)
        private set

    private val _navigateToDetail = MutableStateFlow<Animal?>(null)
    val navigateToDetail: StateFlow<Animal?> = _navigateToDetail.asStateFlow()


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

    fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.AnimalClicked -> {
                _navigateToDetail.value = intent.animal
            }
        }
    }

    fun onNavigationDone() {
        _navigateToDetail.value = null
    }
}