package com.kishan.pmapp.feature_auth.domain.use_case

import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_auth.data.remote.response.User
import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase (
    private val repository: AuthRepository
){
    suspend operator fun invoke(): Resource<User> {
        return repository.authenticate()
    }
}