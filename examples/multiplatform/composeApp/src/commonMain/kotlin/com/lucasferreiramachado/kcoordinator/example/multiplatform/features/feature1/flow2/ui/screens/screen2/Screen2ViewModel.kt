package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2CoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen2ViewModel(
    initialState: Screen2UiState = Screen2UiState(),
    var coordinator: KCoordinator<Flow2CoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<Screen2UiState> = _state.asStateFlow()
    
    fun onEvent(event: Screen2UiEvent) {
        when (event) {
            is Screen2UiEvent.BackButtonPressed -> {
                coordinator?.trigger(Flow2CoordinatorAction.GoBack)
            }
            is Screen2UiEvent.Flow1ButtonPressed -> {
                coordinator?.trigger(Flow2CoordinatorAction.StarFlow1)
            }
        }
    }
}

