package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.home

sealed class HomeUiEvent {
    object SignOutButtonPressed : HomeUiEvent()
    object DashboardButtonPressed : HomeUiEvent()
    object Feature1Flow1ButtonPressed : HomeUiEvent()
    object Feature1Flow2ButtonPressed : HomeUiEvent()
}