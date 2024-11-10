package com.kishan.pmapp.feature_password_entry.presentation.updatePasswordEntry

import com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry.AddPasswordEvent

sealed class UpdatePasswordEvent {
    data class EnteredWebsiteName(val websiteName: String) : UpdatePasswordEvent()
    data class EnteredWebsiteUrl(val websiteUrl: String) : UpdatePasswordEvent()
    data class EnteredSiteUserName(val siteUsername: String) : UpdatePasswordEvent()
    data class EnteredSiteEmail(val siteEmail: String) : UpdatePasswordEvent()
    data class EnteredSitePassword(val sitePassword: String) : UpdatePasswordEvent()
    data class EnteredSiteNote(val siteNote: String) : UpdatePasswordEvent()
    data object OpenDialog : UpdatePasswordEvent()
    data object CloseDialog : UpdatePasswordEvent()
    data object CopyGeneratePassword : UpdatePasswordEvent()
    data object TogglePasswordVisibility : UpdatePasswordEvent()
    data object UpdateEntry : UpdatePasswordEvent()
}