package com.kishan.pmapp.feature_auth.domain.use_case

import com.kishan.pmapp.feature_auth.domain.models.MasterPasswordResult
import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository
import com.kishan.pmapp.feature_auth.util.AuthError

class MasterPasswordUseCase (
    private val repository: AuthRepository
) {
   suspend operator fun invoke(masterPassword:String) : MasterPasswordResult {
       val masterPasswordError = when {
           masterPassword.isBlank() -> AuthError.FieldEmpty
           masterPassword.length < 8 -> AuthError.InputToShort
           else -> null
       }

       if(masterPasswordError != null){
           return MasterPasswordResult(masterPasswordError)
       }
       val result = repository.createMasterPassword(masterPassword.trim())

       return MasterPasswordResult(
           result = result
       )
   }
}
