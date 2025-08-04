package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen3ViewModel(
    initialState: Screen3UiState = Screen3UiState(),
    val coordinator: KCoordinator<Flow1CoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<Screen3UiState> = _state.asStateFlow()
    
    fun onEvent(event: Screen3UiEvent) {
        when (event) {
            is Screen3UiEvent.BackButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.GoBack)
            }
            is Screen3UiEvent.Flow2ButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.StarFlow2)
            }
        }
    }
}

