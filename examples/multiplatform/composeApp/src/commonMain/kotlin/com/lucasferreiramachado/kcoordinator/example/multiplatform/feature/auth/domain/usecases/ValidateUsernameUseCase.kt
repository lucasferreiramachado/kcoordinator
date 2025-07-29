package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.domain.usecases

class ValidateUsernameUseCase {

    fun execute(input: String): String? {
        if (input.length < 4 || input.isBlank()) {
            return "Insert 4 characters username at least"
        }
        return null
    }
}