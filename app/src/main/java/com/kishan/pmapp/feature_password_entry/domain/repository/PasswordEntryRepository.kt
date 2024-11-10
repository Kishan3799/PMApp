package com.kishan.pmapp.feature_password_entry.domain.repository

import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource

interface PasswordEntryRepository {

    suspend fun addPassword(
        websiteName: String,
        websiteUrl : String,
        siteUserName : String,
        siteEmail : String,
        sitePassword : String,
        siteNote : String
    ) : SimpleResource

    suspend fun fetchAllPasswords() : Resource<List<PasswordEntry>>

    suspend fun updatePassword(
        websiteName: String,
        websiteUrl : String,
        siteUserName : String,
        siteEmail : String,
        sitePassword : String,
        siteNote : String,
        id : String
    ) : SimpleResource

    suspend fun deletePassword(id : String) : SimpleResource

    fun logout()
}