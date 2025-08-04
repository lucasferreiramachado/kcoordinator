package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.Screen2ViewModel

@Composable
fun Screen2(
    viewModel: Screen2ViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Screen2View(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}