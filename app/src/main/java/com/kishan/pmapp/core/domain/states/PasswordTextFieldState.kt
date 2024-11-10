package com.kishan.pmapp.core.domain.states

import com.kishan.pmapp.feature_auth.util.AuthError

data class PasswordTextFieldState(
    val text: String = "",
    val isPasswordVisible: Boolean = false,
    val error: AuthError? = null

)