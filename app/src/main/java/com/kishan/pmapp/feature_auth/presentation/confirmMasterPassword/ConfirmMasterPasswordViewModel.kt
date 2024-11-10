package com.kishan.pmapp.feature_auth.presentation.confirmMasterPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.domain.states.PasswordTextFieldState
import com.kishan.pmapp.core.domain.use_case.GetOwnUserEmailUseCase
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_auth.domain.use_case.ConfirmMasterPasswordUseCase
import com.kishan.pmapp.feature_auth.presentation.masterpassword.SetMasterPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmMasterPasswordViewModel @Inject constructor(
    private val confirmMasterPasswordUseCase: ConfirmMasterPasswordUseCase,
    getOwnUserEmail : GetOwnUserEmailUseCase
):ViewModel() {
    private val _currentUserEmail = mutableStateOf(getOwnUserEmail())
    val currentUserEmail : State<String> = _currentUserEmail

    private val _confirmMasterPassword = mutableStateOf(PasswordTextFieldState())
    val confirmMasterPassword: State<PasswordTextFieldState> = _confirmMasterPassword

    private val _conFirmMasterPasswordState = mutableStateOf(ConfirmMasterPasswordState())
    val confirmMasterPasswordState : State<ConfirmMasterPasswordState> = _conFirmMasterPasswordState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()



    fun onEvent(event:ConfirmMasterPasswordEvent){
        when(event){
            is ConfirmMasterPasswordEvent.EnterConfirmMasterPassword -> {
                _confirmMasterPassword.value = confirmMasterPassword.value.copy(
                    text = event.confirmMasterPassword
                )
            }
            is ConfirmMasterPasswordEvent.TogglePasswordVisibility -> {
                _confirmMasterPassword.value = confirmMasterPassword.value.copy(
                    isPasswordVisible = !confirmMasterPassword.value.isPasswordVisible
                )
            }
            is ConfirmMasterPasswordEvent.ConfirmMasterPassword -> {
                viewModelScope.launch {
                    _conFirmMasterPasswordState.value = confirmMasterPasswordState.value.copy(isLoading = true)
                    val confirmMasterPasswordResult = confirmMasterPasswordUseCase(
                        masterPassword = confirmMasterPassword.value.text,
                    )
                    _conFirmMasterPasswordState.value = confirmMasterPasswordState.value.copy(isLoading = false)
                    if(confirmMasterPasswordResult.confirmMasterPasswordError != null) {
                        _confirmMasterPassword.value = confirmMasterPassword.value.copy(
                            error = confirmMasterPasswordResult.confirmMasterPasswordError
                        )
                    }
                    when(confirmMasterPasswordResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.OnConfirmMasterPassword)
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    confirmMasterPasswordResult.result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                        null -> {}
                    }
                }
            }
        }
    }
}