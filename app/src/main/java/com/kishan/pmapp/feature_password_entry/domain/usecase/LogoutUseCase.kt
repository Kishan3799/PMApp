package com.kishan.pmapp.feature_password_entry.domain.usecase

import com.kishan.pmapp.feature_password_entry.domain.repository.PasswordEntryRepository

class LogoutUseCase (
    private val repository: PasswordEntryRepository
) {
    operator fun invoke() {
        repository.logout()
    }

}