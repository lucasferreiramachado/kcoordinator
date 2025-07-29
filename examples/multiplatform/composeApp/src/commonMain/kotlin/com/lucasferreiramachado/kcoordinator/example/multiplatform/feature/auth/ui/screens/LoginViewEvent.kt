package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.ui.screens

sealed class LoginViewEvent {
    data class UsernameChanged(val username: String) : LoginViewEvent()
    data class PasswordChanged(val password: String) : LoginViewEvent()
    object PasswordVisibilityChanged : LoginViewEvent()
    object SignInButtonPressed : LoginViewEvent()
}