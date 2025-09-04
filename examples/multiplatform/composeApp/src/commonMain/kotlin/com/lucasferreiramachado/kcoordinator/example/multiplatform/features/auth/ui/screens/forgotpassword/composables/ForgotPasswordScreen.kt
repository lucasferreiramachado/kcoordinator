package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgotPasswordView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}