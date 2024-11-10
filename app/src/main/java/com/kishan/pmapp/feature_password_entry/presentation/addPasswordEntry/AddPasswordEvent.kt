package com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry

sealed class AddPasswordEvent {
    data class EnteredWebsiteName(val websiteName: String) : AddPasswordEvent()
    data class EnteredWebsiteUrl(val websiteUrl: String) : AddPasswordEvent()
    data class EnteredSiteUserName(val siteUsername: String) : AddPasswordEvent()
    data class EnteredSiteEmail(val siteEmail: String) : AddPasswordEvent()
    data class EnteredSitePassword(val sitePassword: String) : AddPasswordEvent()
    data class EnteredSiteNote(val siteNote: String) : AddPasswordEvent()
    data object OpenDialog : AddPasswordEvent()
    data object CloseDialog : AddPasswordEvent()
    data object CopyGeneratePassword : AddPasswordEvent()
    data object TogglePasswordVisibility : AddPasswordEvent()
    data object SaveEntry : AddPasswordEvent()
}