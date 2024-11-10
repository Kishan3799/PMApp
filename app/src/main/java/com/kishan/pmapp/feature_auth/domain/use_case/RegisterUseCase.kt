package com.kishan.pmapp.feature_auth.domain.use_case

import com.kishan.pmapp.feature_auth.domain.models.RegisterResult
import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository
import com.kishan.pmapp.feature_auth.util.AuthError

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String
    ): RegisterResult {
        val emailError = if (email.isBlank()) AuthError.FieldEmpty else null
        val usernameError = if (username.isBlank()) AuthError.FieldEmpty else null
        val passwordError = when {
            password.isBlank() -> AuthError.FieldEmpty
            password.length < 8 -> AuthError.InputToShort
            else -> null

        }

        if(emailError != null || usernameError != null || passwordError != null) {
            return RegisterResult(emailError, usernameError, passwordError)
        }

        val result = repository.register(username.trim(), email.trim(), password.trim())

        return RegisterResult(
            result = result
        )
    }
}