package com.kishan.pmapp.feature_auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.kishan.pmapp.R
import com.kishan.pmapp.core.utils.Constants
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_auth.data.remote.AuthApi
import com.kishan.pmapp.feature_auth.data.remote.request.AuthCreateAccountRequest
import com.kishan.pmapp.feature_auth.data.remote.request.AuthLoginRequest
import com.kishan.pmapp.feature_auth.data.remote.request.MasterPasswordRequest
import com.kishan.pmapp.feature_auth.data.remote.response.User

import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl (
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String,
    ): SimpleResource {
        val request = AuthCreateAccountRequest(username,email,password)
        return try {
            val response = api.register(request)
            if(response.success){
                Log.d("AuthRepositoryImpl", "register: ${response.data}")
                Resource.Success(Unit)

            }else{
                response.message?.let { msg ->
                    Log.d("AuthRepositoryImpl", "register: $msg")
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }catch (e: HttpException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )

        }
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        val request = AuthLoginRequest(email,password)
        return try {
            val response = api.login(request)
            if(response.success) {
                response.data.let { loginResponse ->

                    println("Overriding token with ${loginResponse.accessToken}")
                    sharedPreferences.edit()
                        .putString(Constants.KEY_JWT_TOKEN , loginResponse.accessToken)
                        .putString(Constants.KEY_USER_EMAIL, loginResponse.user.email)
                        .apply()
                }
                Resource.Success(Unit)
            }else {
                response.message.let { msg->
                    Log.d("AuthRepositoryImpl", "login: $msg")
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }catch (e:HttpException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun authenticate(): Resource<User> {
        return try {
            val response = api.authenticate()
            if(response.success){
                Log.d("AuthRepositoryImpl", "authenticate: ${response.data}")
                response.data.user.let { user ->
                    Resource.Success(user.copy(
                        _id = user._id,
                        createdAt = user.createdAt,
                        updatedAt = user.updatedAt,
                        username = user.username,
                        hasMasterPassword = user.hasMasterPassword,
                        v = user.v
                    ))
                }
            }else{
                Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }catch (e:HttpException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun createMasterPassword(masterPassword: String): SimpleResource {
        val request = MasterPasswordRequest(masterPassword)
        return try {
            val response = api.createMasterPassword(request = request)
            if(response.success){
                Resource.Success(Unit)
            }else{
              response.message?.let { msg ->
                  Resource.Error(UiText.DynamicString(msg))
              } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }catch (e:HttpException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun confirmMasterPassword(masterPassword: String): SimpleResource {
        val request = MasterPasswordRequest(masterPassword)
        return try {
            val response = api.confirmMasterPassword(request = request)
            if(response.success){
                Resource.Success(Unit)
            }else{
                response.message?.let {
                    Resource.Error(UiText.DynamicString(it))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }catch (e:HttpException){
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }


}


