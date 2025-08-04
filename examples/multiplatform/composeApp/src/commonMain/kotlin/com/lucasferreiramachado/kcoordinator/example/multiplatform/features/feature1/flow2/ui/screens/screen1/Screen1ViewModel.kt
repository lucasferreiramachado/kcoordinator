package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen1

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2CoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen1ViewModel(
    initialState: Screen1UiState = Screen1UiState(),
    var coordinator: KCoordinator<Flow2CoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<Screen1UiState> = _state.asStateFlow()
    
    fun onEvent(event: Screen1UiEvent) {
        when (event) {
            is Screen1UiEvent.BackButtonPressed -> {
                coordinator?.trigger(Flow2CoordinatorAction.GoBack)
            }
            is Screen1UiEvent.NextScreenButtonPressed -> {
                coordinator?.trigger(Flow2CoordinatorAction.ShowScreen2)
            }
        }
    }
}