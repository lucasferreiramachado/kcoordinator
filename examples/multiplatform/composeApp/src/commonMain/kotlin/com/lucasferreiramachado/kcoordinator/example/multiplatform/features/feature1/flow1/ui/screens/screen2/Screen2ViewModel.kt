package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen2

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen2ViewModel(
    initialState: Screen2UiState = Screen2UiState(),
    val coordinator: KCoordinator<Flow1CoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<Screen2UiState> = _state.asStateFlow()
    
    fun onEvent(event: Screen2UiEvent) {
        when (event) {
            is Screen2UiEvent.BackButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.GoBack)
            }
            is Screen2UiEvent.NextScreenButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.ShowScreen3)
            }
        }
    }
}

