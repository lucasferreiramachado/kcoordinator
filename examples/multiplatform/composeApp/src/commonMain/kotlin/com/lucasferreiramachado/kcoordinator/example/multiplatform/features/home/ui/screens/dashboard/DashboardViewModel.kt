package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    initialState: DashboardUiState = DashboardUiState()
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<DashboardUiState> = _state.asStateFlow()
    var coordinator: KCoordinator<HomeCoordinatorAction>? = null
    
    fun onEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.BackButtonPressed -> {
                coordinator?.trigger(HomeCoordinatorAction.GoBack)
            }
        }
    }
}

