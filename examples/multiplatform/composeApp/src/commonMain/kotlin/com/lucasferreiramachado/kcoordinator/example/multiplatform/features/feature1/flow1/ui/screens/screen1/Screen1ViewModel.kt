package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen1ViewModel(
    initialState: Screen1UiState = Screen1UiState(),
    val coordinator: KCoordinator<Flow1CoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<Screen1UiState> = _state.asStateFlow()
    
    fun onEvent(event: Screen1UiEvent) {
        when (event) {
            is Screen1UiEvent.BackButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.GoBack)
            }
            is Screen1UiEvent.NextScreenButtonPressed -> {
                coordinator?.trigger(Flow1CoordinatorAction.ShowScreen2)
            }
        }
    }
}