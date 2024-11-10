package com.kishan.pmapp.feature_auth.presentation.confirmMasterPassword

sealed class ConfirmMasterPasswordEvent{
    data class EnterConfirmMasterPassword(val confirmMasterPassword: String) : ConfirmMasterPasswordEvent()
    data object TogglePasswordVisibility : ConfirmMasterPasswordEvent()
    data object ConfirmMasterPassword : ConfirmMasterPasswordEvent()
}