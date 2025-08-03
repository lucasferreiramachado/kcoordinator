package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.login

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.domain.usecases.ValidateLoginInputUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.domain.usecases.ValidatePasswordUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.domain.usecases.ValidateUsernameUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.login.ui.screens.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    initialState: LoginUiState = LoginUiState()
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    var coordinator: KCoordinator<AuthCoordinatorAction>? = null

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged -> {
                val username = event.username
                val usernameError = ValidateUsernameUseCase().execute(username)
                _state.update { state ->
                    state.copy(
                        username = username,
                        usernameError = usernameError
                    )
                }
            }
            is LoginUiEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = ValidatePasswordUseCase().execute(password)
                _state.update { state ->
                    state.copy(
                        password = password,
                        passwordError = passwordError
                    )
                }
            }
            is LoginUiEvent.PasswordVisibilityChanged -> {
                _state.update { state ->
                    state.copy(
                        isVisiblePassword = !state.isVisiblePassword,
                    )
                }
            }
            is LoginUiEvent.SignInButtonPressed -> {
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
                    val username: String = state.value.username
                    coordinator?.trigger(AuthCoordinatorAction.Authenticated(username))
                }
            }
            is LoginUiEvent.ResetPasswordButtonPressed -> {
                coordinator?.trigger(AuthCoordinatorAction.ShowForgotPasswordScreen)
            }
        }
    }
}

