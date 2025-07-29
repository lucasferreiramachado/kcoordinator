package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.domain.usecases

class ValidatePasswordUseCase {

    fun execute(input: String): String? {
        if (input.length < 6 || input.isBlank()) {
            return "Insert 6 characters password at least"
        }
        return null
    }
}