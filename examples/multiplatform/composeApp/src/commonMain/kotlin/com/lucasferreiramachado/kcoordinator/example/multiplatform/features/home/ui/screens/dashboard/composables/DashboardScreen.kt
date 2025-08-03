package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DashboardView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}