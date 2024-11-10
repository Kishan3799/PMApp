package com.kishan.pmapp.feature_password_entry.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.feature_password_entry.domain.usecase.DeletePasswordEntryUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.FetchPasswordEntryUseCase
import com.kishan.pmapp.feature_password_entry.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchPasswordViewModel @Inject constructor(
    private val fetchPasswordEntryUseCase: FetchPasswordEntryUseCase,
    private val deletePasswordEntryUseCase: DeletePasswordEntryUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FetchPasswordEntryState())
    val state: State<FetchPasswordEntryState> = _state

    init {
        loadPasswordEntries()
    }

    private fun loadPasswordEntries() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when(val result = fetchPasswordEntryUseCase()){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        passwordEntries = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun deletePasswordEntry(id: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when(deletePasswordEntryUseCase(id)){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    loadPasswordEntries()
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun logout() {
        logoutUseCase()
    }
}