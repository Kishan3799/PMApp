package com.kishan.pmapp.feature_password_entry.data.repository

import android.content.SharedPreferences
import com.kishan.pmapp.R
import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.core.utils.Constants.KEY_JWT_TOKEN
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_password_entry.data.remote.PasswordEntryApi
import com.kishan.pmapp.feature_password_entry.data.remote.request.AddPasswordRequest
import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository
import retrofit2.HttpException
import java.io.IOException

class PasswordEntryRepositoryImpl(
    private val api: PasswordEntryApi,
    private val sharedPreferences: SharedPreferences
): PasswordEntryRepository {


    override suspend fun addPassword(
        websiteName: String,
        websiteUrl: String,
        siteUserName: String,
        siteEmail: String,
        sitePassword: String,
        siteNote: String,
    ): SimpleResource {
        val request = AddPasswordRequest(
            websiteName = websiteName,
            websiteUrl = websiteUrl,
            siteUsername = siteUserName,
            siteEmail = siteEmail,
            sitePassword = sitePassword,
            siteNote = siteNote,
        )
        return try{
            val response = api.addPassword(request)
            if(response.success){
                Resource.Success(Unit)
            }else{
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        }catch (e: HttpException){
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }


    override suspend fun fetchAllPasswords(): Resource<List<PasswordEntry>> {
        return try{
            val response = api.fetchAllPasswords()
            if(response.success){
                Resource.Success(data = response.data?.map { it.toPasswordEntry() } ?: emptyList())
            }else{
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        }catch (e: HttpException){
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override suspend fun updatePassword(
        websiteName: String,
        websiteUrl: String,
        siteUserName: String,
        siteEmail: String,
        sitePassword: String,
        siteNote: String,
        id: String
    ): SimpleResource {
        val request = AddPasswordRequest(
            websiteName = websiteName,
            websiteUrl = websiteUrl,
            siteUsername = siteUserName,
            siteEmail = siteEmail,
            sitePassword = sitePassword,
            siteNote = siteNote,
        )
        return try {
            val response = api.updatePassword(request, id)
            if(response.success){
                Resource.Success(Unit)
            }else{
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        }catch (e:IOException){
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        }catch (e: HttpException){
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }

    }

    override suspend fun deletePassword(id: String): SimpleResource {
        return try {
            val response = api.deletePassword(id)
            if (response.success) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override fun logout() {
        sharedPreferences.edit()
            .putString(KEY_JWT_TOKEN, "")
            .apply()
    }

}