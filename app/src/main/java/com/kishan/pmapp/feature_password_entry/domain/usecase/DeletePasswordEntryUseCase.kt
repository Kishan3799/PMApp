package com.kishan.pmapp.feature_password_entry.domain.usecase

import com.kishan.pmapp.core.utils.SimpleResource
import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository

class DeletePasswordEntryUseCase(
    private val repository: PasswordEntryRepository
) {
    suspend operator fun invoke(id: String): SimpleResource {
        return repository.deletePassword(id)
    }
}