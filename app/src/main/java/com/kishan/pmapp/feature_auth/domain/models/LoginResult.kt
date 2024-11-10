package com.kishan.pmapp.feature_auth.domain.models

import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_auth.data.remote.response.LoginResponse
import com.kishan.pmapp.feature_auth.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)