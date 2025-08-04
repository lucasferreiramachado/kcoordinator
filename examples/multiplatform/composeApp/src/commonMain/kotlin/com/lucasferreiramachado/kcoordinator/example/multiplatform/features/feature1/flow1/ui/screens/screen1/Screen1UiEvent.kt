package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1

sealed class Screen1UiEvent {
    object BackButtonPressed : Screen1UiEvent()
    object NextScreenButtonPressed : Screen1UiEvent()
}