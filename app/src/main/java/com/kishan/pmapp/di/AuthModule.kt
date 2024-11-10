package com.kishan.pmapp.di

import android.content.SharedPreferences
import com.kishan.pmapp.feature_auth.data.remote.AuthApi
import com.kishan.pmapp.core.utils.Constants.BASE_URL
import com.kishan.pmapp.feature_auth.data.repository.AuthRepositoryImpl
import com.kishan.pmapp.feature_auth.domain.repository.AuthRepository
import com.kishan.pmapp.feature_auth.domain.use_case.AuthenticateUseCase
import com.kishan.pmapp.feature_auth.domain.use_case.ConfirmMasterPasswordUseCase
import com.kishan.pmapp.feature_auth.domain.use_case.LoginUseCase
import com.kishan.pmapp.feature_auth.domain.use_case.MasterPasswordUseCase
import com.kishan.pmapp.feature_auth.domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun providesAuthApi(client: OkHttpClient) : AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(api: AuthApi, sharedPreferences: SharedPreferences) : AuthRepository {
        return AuthRepositoryImpl(api, sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesRegisterUseCase(repository: AuthRepository) : RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository) : LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesAuthenticationUseCase(repository: AuthRepository) : AuthenticateUseCase {
        return AuthenticateUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesMasterPasswordUseCase(repository: AuthRepository) : MasterPasswordUseCase {
        return MasterPasswordUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesConfirmMasterPasswordUseCase(repository: AuthRepository) : ConfirmMasterPasswordUseCase {
        return ConfirmMasterPasswordUseCase(repository)
    }


}