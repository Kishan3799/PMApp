package com.kishan.pmapp.feature_auth.domain.repository

import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_auth.data.remote.response.User

interface AuthRepository {

    suspend fun register(
        username:String,
        email:String,
        password:String,
    ) : SimpleResource

    suspend fun login(
        email:String,
        password:String,
    ) : SimpleResource

    suspend fun authenticate() : Resource<User>

    suspend fun createMasterPassword(
        masterPassword:String
    ) : SimpleResource

    suspend fun confirmMasterPassword(
        masterPassword:String
    ) : SimpleResource


}

