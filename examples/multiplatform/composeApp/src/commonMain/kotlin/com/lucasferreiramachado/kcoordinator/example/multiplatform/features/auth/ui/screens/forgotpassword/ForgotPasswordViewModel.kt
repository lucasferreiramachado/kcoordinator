package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.domain.usecases.ValidateUsernameUseCase
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.flow.login.ui.screens.forgotpassword.ForgotPasswordEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel(
    initialState: ForgotPasswordUiState = ForgotPasswordUiState(),
    val coordinator: KCoordinator<AuthCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<ForgotPasswordUiState> = _state.asStateFlow()
    
    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.UsernameChanged -> validate(username = event.username)
            is ForgotPasswordEvent.ResetButtonPressed -> {
                if (validate(username = state.value.username)) {
                    coordinator?.trigger(AuthCoordinatorAction.GoBack)
                }
            }
        }
    }

    private fun validate(username: String): Boolean {
        val usernameError = ValidateUsernameUseCase().execute(username)
        _state.update { state ->
            state.copy(
                username = username,
                usernameError = usernameError
            )
        }
        return usernameError == null
    }
}

