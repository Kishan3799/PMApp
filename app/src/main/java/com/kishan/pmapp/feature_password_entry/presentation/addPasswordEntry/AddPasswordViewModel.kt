package com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.domain.states.PasswordEntryTextFieldState
import com.kishan.pmapp.core.domain.states.PasswordTextFieldState
import com.kishan.pmapp.core.domain.states.StandardTextFieldState
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.core.utils.UiText
import com.kishan.pmapp.feature_password_entry.domain.usecase.AddPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPasswordViewModel @Inject constructor(
    private val addPasswordUseCase: AddPasswordUseCase
) : ViewModel(){

    private val _websiteNameState = mutableStateOf(PasswordEntryTextFieldState())
    val websiteNameState: State<PasswordEntryTextFieldState> = _websiteNameState

    private val _websiteUrlState = mutableStateOf(PasswordEntryTextFieldState())
    val websiteUrlState: State<PasswordEntryTextFieldState> = _websiteUrlState

    private val _siteUserNameState = mutableStateOf(PasswordEntryTextFieldState())
    val siteUserNameState: State<PasswordEntryTextFieldState> = _siteUserNameState

    private val _siteEmailState = mutableStateOf(PasswordEntryTextFieldState())
    val siteEmailState: State<PasswordEntryTextFieldState> = _siteEmailState

    private val _sitePasswordState = mutableStateOf(PasswordEntryTextFieldState())
    val sitePasswordState: State<PasswordEntryTextFieldState> = _sitePasswordState

    private val _siteNoteState = mutableStateOf(PasswordEntryTextFieldState())
    val siteNoteState: State<PasswordEntryTextFieldState> = _siteNoteState

    private val _addPasswordState = mutableStateOf(AddPasswordState())
    val addPasswordState: State<AddPasswordState> = _addPasswordState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event:AddPasswordEvent){
        when(event){
            is AddPasswordEvent.EnteredWebsiteName -> {
                _websiteNameState.value = websiteNameState.value.copy(
                    text = event.websiteName
                )
            }
            AddPasswordEvent.CopyGeneratePassword -> TODO()
            is AddPasswordEvent.EnteredSiteEmail -> {
                _siteEmailState.value = siteEmailState.value.copy(
                    text = event.siteEmail
                )
            }
            is AddPasswordEvent.EnteredSiteNote -> {
                _siteNoteState.value = siteNoteState.value.copy(
                    text = event.siteNote
                )
            }
            is AddPasswordEvent.EnteredSitePassword -> {
                _sitePasswordState.value = sitePasswordState.value.copy(
                    text = event.sitePassword
                )
            }
            is AddPasswordEvent.EnteredSiteUserName -> {
                _siteUserNameState.value = siteUserNameState.value.copy(
                    text = event.siteUsername
                )
            }
            is AddPasswordEvent.EnteredWebsiteUrl -> {
                _websiteUrlState.value = websiteUrlState.value.copy(
                    text = event.websiteUrl
                )
            }
            AddPasswordEvent.TogglePasswordVisibility -> {
                _addPasswordState.value = addPasswordState.value.copy(
                    isPasswordVisible = !addPasswordState.value.isPasswordVisible
                )
            }
            AddPasswordEvent.CloseDialog -> {
                _addPasswordState.value = addPasswordState.value.copy(
                    openDialog = false
                )
            }
            AddPasswordEvent.OpenDialog -> {
                _addPasswordState.value = addPasswordState.value.copy(
                    openDialog = !addPasswordState.value.openDialog
                )
            }
            AddPasswordEvent.SaveEntry -> {
                viewModelScope.launch {
                    _addPasswordState.value = addPasswordState.value.copy(isLoading = true)
                    val addPasswordResult = addPasswordUseCase(
                        websiteName = websiteNameState.value.text,
                        websiteUrl = websiteUrlState.value.text,
                        siteUserName = siteUserNameState.value.text,
                        siteEmail = siteEmailState.value.text,
                        sitePassword = sitePasswordState.value.text,
                        siteNote = siteNoteState.value.text
                    )
                    _addPasswordState.value = addPasswordState.value.copy(isLoading = false)
                    if(addPasswordResult.websiteNameError != null) {
                        _websiteNameState.value = websiteNameState.value.copy(
                            error = addPasswordResult.websiteNameError
                        )
                    }
                    if(addPasswordResult.websiteUrlError != null) {
                        _websiteUrlState.value = websiteUrlState.value.copy(
                            error = addPasswordResult.websiteUrlError
                        )
                    }
                    if(addPasswordResult.siteUserNameError != null) {
                        _siteUserNameState.value = siteUserNameState.value.copy(
                            error = addPasswordResult.siteUserNameError
                        )
                    }
                    if(addPasswordResult.siteEmailError != null) {
                        _siteEmailState.value = siteEmailState.value.copy(
                            error = addPasswordResult.siteEmailError
                        )
                    }
                    if(addPasswordResult.sitePasswordError != null) {
                        _sitePasswordState.value = sitePasswordState.value.copy(
                            error = addPasswordResult.sitePasswordError
                        )
                    }
                    when(addPasswordResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.onSavePassword)
                            _websiteNameState.value = PasswordEntryTextFieldState()
                            _websiteUrlState.value = PasswordEntryTextFieldState()
                            _siteUserNameState.value = PasswordEntryTextFieldState()
                            _siteEmailState.value = PasswordEntryTextFieldState()
                            _sitePasswordState.value = PasswordEntryTextFieldState()
                            _siteNoteState.value = PasswordEntryTextFieldState()
                        }
                        is Resource.Error ->{
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    addPasswordResult.result.uiText ?: UiText.unknownError()
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