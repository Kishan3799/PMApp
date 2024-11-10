package com.kishan.pmapp.feature_auth.util

import com.kishan.pmapp.core.utils.Error

sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPassword : AuthError()
    object InputToShort : AuthError()


}