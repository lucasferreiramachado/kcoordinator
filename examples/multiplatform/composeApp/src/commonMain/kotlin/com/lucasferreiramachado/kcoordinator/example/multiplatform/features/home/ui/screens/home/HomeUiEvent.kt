package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.home

sealed class HomeUiEvent {
    object SignOutButtonPressed : HomeUiEvent()
    object DashboardButtonPressed : HomeUiEvent()
}