package com.kishan.pmapp.feature_auth.domain.models

import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_auth.util.AuthError

data class MasterPasswordResult(
    val masterPasswordError: AuthError? = null,
    val result: SimpleResource? = null,
)
