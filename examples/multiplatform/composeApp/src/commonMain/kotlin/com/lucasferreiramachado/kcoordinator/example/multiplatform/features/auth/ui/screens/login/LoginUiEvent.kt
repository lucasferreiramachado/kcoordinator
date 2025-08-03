package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.login

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object PasswordVisibilityChanged : LoginUiEvent()
    object SignInButtonPressed : LoginUiEvent()
    object ResetPasswordButtonPressed : LoginUiEvent()
}