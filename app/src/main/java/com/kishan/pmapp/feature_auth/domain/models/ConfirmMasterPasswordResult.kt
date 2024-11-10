package com.kishan.pmapp.feature_auth.domain.models

import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_auth.util.AuthError

data class ConfirmMasterPasswordResult(
    val confirmMasterPasswordError: AuthError? = null,
    val result: SimpleResource? = null,
)
