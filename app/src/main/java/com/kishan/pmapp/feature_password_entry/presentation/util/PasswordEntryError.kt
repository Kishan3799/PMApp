package com.kishan.pmapp.feature_password_entry.presentation.util

sealed class PasswordEntryError : Error() {
    data object EntryFieldEmpty : PasswordEntryError()
    data object PasswordIsShort : PasswordEntryError()
    data object PasswordIsWeak : PasswordEntryError()
}