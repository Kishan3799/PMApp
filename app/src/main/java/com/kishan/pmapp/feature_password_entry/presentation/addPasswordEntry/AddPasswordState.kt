package com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry

data class AddPasswordState (
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val openDialog: Boolean = false
)