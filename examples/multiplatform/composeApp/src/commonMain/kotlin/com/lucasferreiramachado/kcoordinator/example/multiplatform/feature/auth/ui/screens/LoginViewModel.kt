package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.ui.screens

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.domain.usecases.ValidateLoginInputUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.domain.usecases.ValidatePasswordUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.domain.usecases.ValidateUsernameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    initialState: LoginViewState = LoginViewState()
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<LoginViewState> = _state.asStateFlow()

    var onUserAuthenticated: (String) -> Unit = { }
    
    fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.UsernameChanged -> {
                val username = event.username
                val usernameError = ValidateUsernameUseCase().execute(username)
                _state.update { state ->
                    state.copy(
                        username = username,
                        usernameError = usernameError
                    )
                }
            }
            is LoginViewEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = ValidatePasswordUseCase().execute(password)
                _state.update { state ->
                    state.copy(
                        password = password,
                        passwordError = passwordError
                    )
                }
            }
            is LoginViewEvent.PasswordVisibilityChanged -> {
                _state.update { state ->
                    state.copy(
                       isVisiblePassword = !state.isVisiblePassword,
                    )
                }
            }
            is LoginViewEvent.SignInButtonPressed -> {
                val result = ValidateLoginInputUseCase().execute(
                    state.value.username,
                    state.value.password
                )
                _state.update { state ->
                    state.copy(
                        passwordError = result.passwordError,
                        usernameError = result.usernameError,
                    )
                }
                if (result.isValid) {
                    val userId: String = state.value.username
                    onUserAuthenticated(userId)
                }
            }
        }
    }
}

