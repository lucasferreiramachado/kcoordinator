package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen2

sealed class Screen2UiEvent {
    object BackButtonPressed : Screen2UiEvent()
    object NextScreenButtonPressed : Screen2UiEvent()
}