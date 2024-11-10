package com.kishan.pmapp.di

import android.content.SharedPreferences
import com.kishan.pmapp.core.utils.Constants.BASE_URL
import com.kishan.pmapp.feature_password_entry.data.remote.PasswordEntryApi
import com.kishan.pmapp.feature_password_entry.data.repository.PasswordEntryRepositoryImpl
import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository
import com.kishan.pmapp.feature_password_entry.domain.usecase.AddPasswordUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.DeletePasswordEntryUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.FetchPasswordEntryUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.LogoutUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.UpdatePasswordUseCase
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
object PasswordEntryModule {

    @Provides
    @Singleton
    fun providesPasswordEntryApi(client: OkHttpClient) : PasswordEntryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PasswordEntryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPasswordEntryRepository(api: PasswordEntryApi, sharedPreferences: SharedPreferences) : PasswordEntryRepository {
        return PasswordEntryRepositoryImpl(api,sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesAddPasswordUseCase(repository: PasswordEntryRepository) : AddPasswordUseCase {
        return AddPasswordUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFetchPasswordEntryUseCase(repository: PasswordEntryRepository) : FetchPasswordEntryUseCase {
        return FetchPasswordEntryUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesUpdatePasswordEntryUseCase(repository: PasswordEntryRepository) : UpdatePasswordUseCase {
        return UpdatePasswordUseCase(repository)
    }


    @Provides
    @Singleton
    fun providesDeletePasswordEntryUseCase(repository: PasswordEntryRepository) : DeletePasswordEntryUseCase {
        return DeletePasswordEntryUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesLogoutUseCase(repository: PasswordEntryRepository) : LogoutUseCase {
        return LogoutUseCase(repository)
    }

}