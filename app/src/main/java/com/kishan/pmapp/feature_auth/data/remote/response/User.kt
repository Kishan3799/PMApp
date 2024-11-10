package com.kishan.pmapp.feature_auth.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_id")
    val _id: String?,
    val createdAt: String,
    val email: String,
    val updatedAt: String,
    val username: String,
    val hasMasterPassword: Boolean,
    @SerialName("__v")
    val v: Int,
)