package com.kishan.pmapp.feature_password_entry.data.remote.request

data class AddPasswordRequest(
    val websiteName: String,
    val websiteUrl : String,
    val siteUsername : String,
    val siteEmail : String,
    val sitePassword : String,
    val siteNote : String? = null
)
