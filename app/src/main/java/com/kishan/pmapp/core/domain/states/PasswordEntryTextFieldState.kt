package com.kishan.pmapp.core.domain.states

import com.kishan.pmapp.feature_password_entry.presentation.util.PasswordEntryError

data class PasswordEntryTextFieldState(
    val text: String = "",
    val error: PasswordEntryError? = null
)
