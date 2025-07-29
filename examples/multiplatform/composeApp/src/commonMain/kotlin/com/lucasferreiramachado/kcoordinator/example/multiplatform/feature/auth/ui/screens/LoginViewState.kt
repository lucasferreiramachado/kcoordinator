package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.ui.screens

data class LoginViewState(
    var username: String = "",
    var usernameError: String? = null,
    var password: String = "",
    var passwordError: String? = null,
    var isVisiblePassword: Boolean = false,
    var signInButtonEnabled: Boolean = true
)