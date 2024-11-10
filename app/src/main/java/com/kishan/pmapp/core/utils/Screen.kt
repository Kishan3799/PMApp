package com.kishan.pmapp.core.utils

import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.feature_password_entry.data.remote.response.PasswordEntryResponse
import kotlinx.serialization.Serializable

@Serializable
object LoginScreen

@Serializable
object RegisterScreen

@Serializable
object MasterPasswordScreen

@Serializable
object ConfirmMasterPasswordScreen

@Serializable
object SplashScreen

//HomeScreens
@Serializable
object HomeScreen

@Serializable
object AddPasswordEntryScreen

@Serializable
data class ShowPasswordScreen(
    val id: String,
    val websiteName: String,
    val websiteUrl: String,
    val siteUsername: String,
    val siteEmail: String,
    val sitePassword: String,
    val siteNote: String,
    val userId: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class UpdatePasswordEntryScreen(
    val id: String,
    val websiteName: String,
    val websiteUrl: String,
    val siteUsername: String,
    val siteEmail: String,
    val sitePassword: String,
    val siteNote: String,
    val userId: String,
    val createdAt: String,
    val updatedAt: String
)

