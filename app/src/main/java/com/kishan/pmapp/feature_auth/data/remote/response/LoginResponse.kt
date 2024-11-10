package com.kishan.pmapp.feature_auth.data.remote.response

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)