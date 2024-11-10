package com.kishan.pmapp.feature_auth.domain.use_case

import com.kishan.pmapp.feature_auth.domain.models.ConfirmMasterPasswordResult
import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository
import com.kishan.pmapp.feature_auth.util.AuthError

class ConfirmMasterPasswordUseCase (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(masterPassword:String) : ConfirmMasterPasswordResult {
        val confirmMasterPasswordError = when {
            masterPassword.isBlank() -> AuthError.FieldEmpty
            masterPassword.length < 8 -> AuthError.InputToShort
            else -> null
        }

        if(confirmMasterPasswordError != null){
            return ConfirmMasterPasswordResult(confirmMasterPasswordError)
        }
        val result = repository.confirmMasterPassword(masterPassword.trim())

        return ConfirmMasterPasswordResult(
            result = result
        )
    }
}