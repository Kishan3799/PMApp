package com.kishan.pmapp.feature_password_entry.presentation.home

import com.kishan.pmapp.core.data.PasswordEntry

data class FetchPasswordEntryState(
    val isLoading: Boolean = false,
    val passwordEntries: List<PasswordEntry> = emptyList(),
)
