package com.kishan.pmapp.feature_auth.presentation.masterpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.domain.states.PasswordTextFieldState
import com.kishan.pmapp.core.domain.use_case.GetOwnUserEmailUseCase
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_auth.domain.use_case.MasterPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterPasswordViewModel @Inject constructor(
    private val masterPasswordUseCase: MasterPasswordUseCase,
    getOwnUserEmail : GetOwnUserEmailUseCase
): ViewModel() {

    private val _currentUserEmail = mutableStateOf(getOwnUserEmail())
    val currentUserEmail : State<String> = _currentUserEmail

    private val _masterPasswordState = mutableStateOf(PasswordTextFieldState())
    val masterPasswordState: State<PasswordTextFieldState> = _masterPasswordState

    private val _setMasterPasswordState = mutableStateOf(SetMasterPasswordState())
    val setMasterPasswordState : State<SetMasterPasswordState> = _setMasterPasswordState


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event:SetMasterPasswordEvent){
        when(event){
            is SetMasterPasswordEvent.EnterMasterPassword -> {
                _masterPasswordState.value = masterPasswordState.value.copy(
                    text = event.masterPassword
                )
            }

            is SetMasterPasswordEvent.TogglePasswordVisibility -> {
                _masterPasswordState.value = masterPasswordState.value.copy(
                    isPasswordVisible = !masterPasswordState.value.isPasswordVisible
                )
            }

            is SetMasterPasswordEvent.SetMasterPassword -> {
                viewModelScope.launch {
                    _setMasterPasswordState.value = setMasterPasswordState.value.copy(isLoading = true)
                    val masterPasswordResult = masterPasswordUseCase(
                        masterPassword = masterPasswordState.value.text,
                    )
                    _setMasterPasswordState.value = setMasterPasswordState.value.copy(isLoading = false)
                    if(masterPasswordResult.masterPasswordError != null) {
                        _masterPasswordState.value = masterPasswordState.value.copy(
                            error = masterPasswordResult.masterPasswordError
                        )
                    }
                    when(masterPasswordResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.OnMasterPassword)
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    masterPasswordResult.result.uiText ?: UiText.unknownError()
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