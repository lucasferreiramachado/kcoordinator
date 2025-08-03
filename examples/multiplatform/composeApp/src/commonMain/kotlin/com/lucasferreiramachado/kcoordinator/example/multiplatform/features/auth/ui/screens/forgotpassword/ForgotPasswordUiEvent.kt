package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.flow.login.ui.screens.forgotpassword

sealed class ForgotPasswordEvent {
    data class UsernameChanged(val username: String) : ForgotPasswordEvent()
    object ResetButtonPressed : ForgotPasswordEvent()
}