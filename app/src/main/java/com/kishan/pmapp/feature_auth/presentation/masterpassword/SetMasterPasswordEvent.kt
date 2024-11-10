package com.kishan.pmapp.feature_auth.presentation.masterpassword

sealed class SetMasterPasswordEvent {
    data class EnterMasterPassword(val masterPassword: String):SetMasterPasswordEvent()
    data object TogglePasswordVisibility: SetMasterPasswordEvent()
    data object SetMasterPassword: SetMasterPasswordEvent();
}