package com.kishan.pmapp.feature_password_entry.domain.usecase

import com.kishan.pmapp.feature_password_entry.domain.models.AddPasswordResult
import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository
import com.kishan.pmapp.feature_password_entry.presentation.util.PasswordEntryError

class AddPasswordUseCase (
    private val repository: PasswordEntryRepository
) {
    suspend operator fun invoke(
        websiteName: String,
        websiteUrl : String,
        siteUserName : String,
        siteEmail : String,
        sitePassword : String,
        siteNote : String
    ) : AddPasswordResult {
        val websiteNameError = if (websiteName.isBlank()) PasswordEntryError.EntryFieldEmpty else null
        val websiteUrlError = if (websiteUrl.isBlank()) PasswordEntryError.EntryFieldEmpty else null
        val siteUserNameError = if (siteUserName.isBlank()) PasswordEntryError.EntryFieldEmpty else null
        val siteEmailError = if (siteEmail.isBlank()) PasswordEntryError.EntryFieldEmpty else null
        val sitePasswordError = when {
            sitePassword.isBlank() -> PasswordEntryError.EntryFieldEmpty
            sitePassword.length < 4 -> PasswordEntryError.PasswordIsShort
            sitePassword.length < 8 -> PasswordEntryError.PasswordIsWeak
            else -> null

        }

        if(websiteNameError != null
            || websiteUrlError != null
            || siteUserNameError != null
            || siteEmailError != null
            || sitePasswordError != null) {
            return AddPasswordResult(
                websiteNameError,
                websiteUrlError,
                siteUserNameError,
                siteEmailError,
                sitePasswordError
            )
        }

        val addPasswordEntryResult = repository.addPassword(
            websiteName,
            websiteUrl,
            siteUserName,
            siteEmail,
            sitePassword,
            siteNote
        )

        return AddPasswordResult(
            result = addPasswordEntryResult
        )


    }
}