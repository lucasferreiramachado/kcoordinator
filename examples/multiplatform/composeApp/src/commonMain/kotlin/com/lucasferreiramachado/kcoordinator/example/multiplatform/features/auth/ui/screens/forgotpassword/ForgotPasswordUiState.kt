package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword

data class ForgotPasswordUiState(
    var username: String = "",
    var usernameError: String? = null,
    var resetButtonEnabled: Boolean = true
)