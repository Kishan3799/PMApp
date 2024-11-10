package com.kishan.pmapp.feature_password_entry.presentation.updatePasswordEntry

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.domain.states.PasswordEntryTextFieldState
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_password_entry.domain.usecase.UpdatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel() {

    val id: String = savedStateHandle.get<String>("id") ?: ""
    private val websiteNameField : String = savedStateHandle.get<String>("websiteName") ?: ""
    private val websiteUrlField : String = savedStateHandle.get<String>("websiteUrl") ?: ""
    private val siteUsernameField : String = savedStateHandle.get<String>("siteUsername") ?: ""
    private val siteEmailField : String = savedStateHandle.get<String>("siteEmail") ?: ""
    private val sitePasswordField : String = savedStateHandle.get<String>("sitePassword") ?: ""
    private val siteNoteField : String = savedStateHandle.get<String>("siteNote") ?: ""

    init {
        Log.d("UpdatePasswordViewModel", "id: $id")
    }

    private val _websiteNameState = mutableStateOf(PasswordEntryTextFieldState(
        text = websiteNameField
    ))
    val websiteNameState: State<PasswordEntryTextFieldState> = _websiteNameState

    private val _websiteUrlState = mutableStateOf(PasswordEntryTextFieldState(
        text = websiteUrlField
    ))
    val websiteUrlState: State<PasswordEntryTextFieldState> = _websiteUrlState

    private val _siteUserNameState = mutableStateOf(PasswordEntryTextFieldState(
        text = siteUsernameField
    ))
    val siteUserNameState: State<PasswordEntryTextFieldState> = _siteUserNameState

    private val _siteEmailState = mutableStateOf(PasswordEntryTextFieldState(
        text = siteEmailField
    ))
    val siteEmailState: State<PasswordEntryTextFieldState> = _siteEmailState

    private val _sitePasswordState = mutableStateOf(PasswordEntryTextFieldState(
        text = sitePasswordField
    ))
    val sitePasswordState: State<PasswordEntryTextFieldState> = _sitePasswordState

    private val _siteNoteState = mutableStateOf(PasswordEntryTextFieldState(
        text = siteNoteField
    ))
    val siteNoteState: State<PasswordEntryTextFieldState> = _siteNoteState

    private val _updatePasswordState = mutableStateOf(UpdatePasswordState())
    val updatePasswordState: State<UpdatePasswordState> = _updatePasswordState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event:UpdatePasswordEvent){
        when(event) {
            is UpdatePasswordEvent.EnteredWebsiteName -> {
                _websiteNameState.value = websiteNameState.value.copy(
                    text = event.websiteName
                )
            }
            is UpdatePasswordEvent.EnteredSiteEmail -> {
                _siteEmailState.value = siteEmailState.value.copy(
                    text = event.siteEmail
                )
            }
            is UpdatePasswordEvent.EnteredSiteNote -> {
                _siteNoteState.value = siteNoteState.value.copy(
                    text = event.siteNote
                )
            }
            is UpdatePasswordEvent.EnteredSitePassword -> {
                _sitePasswordState.value = sitePasswordState.value.copy(
                    text = event.sitePassword
                )
            }
            is UpdatePasswordEvent.EnteredSiteUserName -> {
                _siteUserNameState.value = siteUserNameState.value.copy(
                    text = event.siteUsername
                )
            }
            is UpdatePasswordEvent.EnteredWebsiteUrl -> {
                _websiteUrlState.value = websiteUrlState.value.copy(
                    text = event.websiteUrl
                )
            }
            UpdatePasswordEvent.CloseDialog -> {
                _updatePasswordState.value = updatePasswordState.value.copy(
                    openDialog = false
                )
            }
            UpdatePasswordEvent.CopyGeneratePassword -> TODO()
            UpdatePasswordEvent.OpenDialog -> {
                _updatePasswordState.value = updatePasswordState.value.copy(
                    openDialog = !updatePasswordState.value.openDialog
                )
            }
            UpdatePasswordEvent.TogglePasswordVisibility -> {
                _updatePasswordState.value = updatePasswordState.value.copy(
                    isPasswordVisible = !updatePasswordState.value.isPasswordVisible
                )
            }
            UpdatePasswordEvent.UpdateEntry -> {
                viewModelScope.launch {
                    _updatePasswordState.value = updatePasswordState.value.copy(isLoading = true)
                    val updatePasswordResult = updatePasswordUseCase(
                        websiteName = websiteNameState.value.text,
                        websiteUrl = websiteUrlState.value.text,
                        siteUserName = siteUserNameState.value.text,
                        siteEmail = siteEmailState.value.text,
                        sitePassword = sitePasswordState.value.text,
                        siteNote = siteNoteState.value.text,
                        id = id
                    )
                    _updatePasswordState.value = updatePasswordState.value.copy(
                        isLoading = false
                    )
                    if(updatePasswordResult.websiteNameError != null) {
                        _websiteNameState.value = websiteNameState.value.copy(
                            error = updatePasswordResult.websiteNameError
                        )
                    }
                    if(updatePasswordResult.websiteUrlError != null) {
                        _websiteUrlState.value = websiteUrlState.value.copy(
                            error = updatePasswordResult.websiteUrlError
                        )
                    }
                    if(updatePasswordResult.siteUserNameError != null) {
                        _siteUserNameState.value = siteUserNameState.value.copy(
                            error = updatePasswordResult.siteUserNameError
                        )
                    }
                    if(updatePasswordResult.siteEmailError != null) {
                        _siteEmailState.value = siteEmailState.value.copy(
                            error = updatePasswordResult.siteEmailError
                        )
                    }
                    if(updatePasswordResult.sitePasswordError != null) {
                        _sitePasswordState.value = sitePasswordState.value.copy(
                            error = updatePasswordResult.sitePasswordError
                        )
                    }
                    when(updatePasswordResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.onSavePassword)
                        }
                        is Resource.Error ->{
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    updatePasswordResult.result.uiText ?: UiText.unknownError()
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