package com.kishan.pmapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.kishan.pmapp.core.domain.use_case.GetOwnUserEmailUseCase
import com.kishan.pmapp.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return  app.getSharedPreferences(
            Constants.SHARED_PREF_NAME,  MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPreferences: SharedPreferences) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val accessToken = sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "")
                val modifiedRequest = it.request().newBuilder()
                    .addHeader("Authorization", "$accessToken")
                    .build()
                it.proceed(modifiedRequest)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideGetOwnUserEmailUseCase(sharedPreferences: SharedPreferences) : GetOwnUserEmailUseCase {
        return GetOwnUserEmailUseCase(sharedPreferences)
    }

}