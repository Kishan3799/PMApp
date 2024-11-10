package com.kishan.pmapp.feature_auth.data.remote.request

data class AuthCreateAccountRequest (
    val username:String,
    val email: String,
    val password: String
)