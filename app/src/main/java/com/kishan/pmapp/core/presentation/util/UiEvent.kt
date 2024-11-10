package com.kishan.pmapp.core.presentation.util

import com.kishan.pmapp.core.utils.Event
import com.kishan.pmapp.core.utils.MasterPasswordScreen
import com.kishan.pmapp.core.utils.UiText
import java.util.Objects

sealed class UiEvent : Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate<T:Any>(val route: T ) : UiEvent()
    data object NavigateUp : UiEvent()
    data object OnLogin : UiEvent()
    data object OnMasterPassword : UiEvent()
    data object OnConfirmMasterPassword : UiEvent()
    data object onSavePassword : UiEvent()
    data object onDeletePassword : UiEvent()
    data object onUpdatePassword : UiEvent()

}