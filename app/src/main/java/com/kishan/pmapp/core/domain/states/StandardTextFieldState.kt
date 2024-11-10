package com.kishan.pmapp.core.domain.states

import com.kishan.pmapp.feature_auth.util.AuthError

data class StandardTextFieldState(
    val text: String = "",
    val error: AuthError? = null,
)