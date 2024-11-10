package com.kishan.pmapp.feature_password_entry.domain.models

import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_password_entry.presentation.util.PasswordEntryError

data class AddPasswordResult(
    val websiteNameError: PasswordEntryError? = null,
    val websiteUrlError: PasswordEntryError? = null,
    val siteUserNameError: PasswordEntryError? = null,
    val siteEmailError: PasswordEntryError? = null,
    val sitePasswordError: PasswordEntryError? = null,
//    val siteNoteError: PasswordEntryError? = null,
    val result: SimpleResource? = null
)