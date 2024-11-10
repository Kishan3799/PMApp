package com.kishan.pmapp.feature_password_entry.domain.usecase

import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository

class FetchPasswordEntryUseCase (
    private val repository: PasswordEntryRepository
) {
    suspend operator fun invoke() : Resource<List<PasswordEntry>> {
        return repository.fetchAllPasswords()
    }
}