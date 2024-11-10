package com.kishan.pmapp.feature_password_entry.data.remote

import com.kishan.pmapp.core.data.dto.BasicApiResponse
import com.kishan.pmapp.feature_password_entry.data.remote.request.AddPasswordRequest
import com.kishan.pmapp.feature_password_entry.data.remote.response.PasswordEntryResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PasswordEntryApi {

    //AddPassword Entry
    @POST("/api/v1/create_password")
    suspend fun addPassword(
        @Body request: AddPasswordRequest
    ) : BasicApiResponse<Unit>

    @GET("/api/v1/get_all_passwords")
    suspend fun fetchAllPasswords() : BasicApiResponse<List<PasswordEntryResponse>>

    @POST("/api/v1/update_password/{id}")
    suspend fun updatePassword(
        @Body request: AddPasswordRequest,
        @Path("id") id: String
    ): BasicApiResponse<Unit>

    @DELETE("/api/v1/delete_password/{id}")
    suspend fun deletePassword(
        @Path("id") id: String
    ): BasicApiResponse<Unit>
}


