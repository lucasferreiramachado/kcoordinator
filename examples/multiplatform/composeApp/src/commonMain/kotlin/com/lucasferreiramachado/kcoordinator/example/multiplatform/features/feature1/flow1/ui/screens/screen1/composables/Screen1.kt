package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1.Screen1ViewModel

@Composable
fun Screen1(
    viewModel: Screen1ViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Screen1View(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}