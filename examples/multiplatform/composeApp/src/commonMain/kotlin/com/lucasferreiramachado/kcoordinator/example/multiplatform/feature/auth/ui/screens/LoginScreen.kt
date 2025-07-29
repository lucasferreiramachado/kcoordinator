package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun previewLoginScreen(
    state: LoginViewState,
    events: List<LoginViewEvent>
) {
    val viewModel = LoginViewModel(state)
    events.forEach {  event -> viewModel.onEvent(event) }
    LoginScreen(
        viewModel
    )
}

@Composable
@Preview
fun LoginScreenPreviewSuccessFlow(
    state: LoginViewState = LoginViewState(),
    events: List<LoginViewEvent> = listOf(
        LoginViewEvent.UsernameChanged("lucasferreiramachado"),
        LoginViewEvent.PasswordChanged("123456"),
        LoginViewEvent.PasswordVisibilityChanged
    )
) {
    previewLoginScreen(state, events)
}

@Composable
@Preview
fun LoginScreenPreviewOnShortUsernameError(
    state: LoginViewState = LoginViewState(),
    events: List<LoginViewEvent> = listOf(
        LoginViewEvent.UsernameChanged(" ")
    )
) {
    previewLoginScreen(state, events)
}

@Composable
@Preview
fun LoginScreenPreviewOnShortPasswordError(
    state: LoginViewState = LoginViewState(),
    events: List<LoginViewEvent> = listOf(
        LoginViewEvent.PasswordChanged("123")
    )
) {
    previewLoginScreen(state, events)
}