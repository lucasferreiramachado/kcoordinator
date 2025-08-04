package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2

sealed class Screen2UiEvent {
    object BackButtonPressed : Screen2UiEvent()
    object Flow1ButtonPressed : Screen2UiEvent()
}