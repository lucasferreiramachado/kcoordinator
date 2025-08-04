package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3

sealed class Screen3UiEvent {
    object BackButtonPressed : Screen3UiEvent()
    object Flow2ButtonPressed : Screen3UiEvent()
}