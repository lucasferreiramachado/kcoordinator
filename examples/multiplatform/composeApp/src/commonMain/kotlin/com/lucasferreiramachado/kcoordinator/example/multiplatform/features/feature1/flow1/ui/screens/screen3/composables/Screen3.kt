package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3.Screen3ViewModel

@Composable
fun Screen3(
    viewModel: Screen3ViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Screen3View(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}