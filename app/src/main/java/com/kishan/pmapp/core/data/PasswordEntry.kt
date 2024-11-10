package com.kishan.pmapp.core.data

import kotlinx.serialization.Serializable

@Serializable
data class PasswordEntry (
    val websiteName: String,
    val websiteUrl: String,
    val siteUsername: String,
    val siteEmail: String,
    val sitePassword: String,
    val siteNote: String,
    val id: String,
    val userId: String,
    val updatedAt: String,
    val createdAt: String
)
