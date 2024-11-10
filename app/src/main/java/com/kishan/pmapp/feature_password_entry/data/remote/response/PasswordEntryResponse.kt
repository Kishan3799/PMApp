package com.kishan.pmapp.feature_password_entry.data.remote.response

import com.kishan.pmapp.core.data.PasswordEntry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordEntryResponse(
    @SerialName("__v")
    val v: Int,
    val _id: String,
    val createdAt: String,
    val siteEmail: String,
    val siteNote: String,
    val sitePassword: String,
    val siteUsername: String,
    val updatedAt: String,
    val userId: String,
    val websiteName: String,
    val websiteUrl: String
){
    fun toPasswordEntry() : PasswordEntry {
        return PasswordEntry(
            websiteName = websiteName,
            websiteUrl = websiteUrl,
            siteUsername = siteUsername,
            siteEmail = siteEmail,
            sitePassword = sitePassword,
            siteNote = siteNote,
            id = _id,
            userId = userId,
            updatedAt = updatedAt,
            createdAt = createdAt
        )
    }
}