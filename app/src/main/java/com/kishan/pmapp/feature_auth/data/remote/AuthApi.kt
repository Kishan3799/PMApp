package com.kishan.pmapp.feature_auth.data.remote


import com.kishan.pmapp.core.data.dto.BasicApiResponse
import com.kishan.pmapp.feature_auth.data.remote.request.AuthCreateAccountRequest
import com.kishan.pmapp.feature_auth.data.remote.request.AuthLoginRequest
import com.kishan.pmapp.feature_auth.data.remote.request.MasterPasswordRequest
import com.kishan.pmapp.feature_auth.data.remote.response.LoginResponse
import com.kishan.pmapp.feature_auth.data.remote.response.User
import com.kishan.pmapp.feature_auth.data.remote.response.UserResponse

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/auth/register")
    suspend fun register(
        @Body authUserRequest: AuthCreateAccountRequest
    ) : BasicApiResponse<Unit>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body request: AuthLoginRequest
    ) : LoginResponse

    @GET("/api/v1/auth/me")
    suspend fun authenticate() : UserResponse

    @POST("/api/v1/auth/setMasterPassword")
    suspend fun createMasterPassword(
        @Body request: MasterPasswordRequest
    ) : BasicApiResponse<Unit>

    @POST("/api/v1/auth/checkMasterPassword")
    suspend fun confirmMasterPassword(
        @Body request: MasterPasswordRequest
    ) : BasicApiResponse<Unit>

}


