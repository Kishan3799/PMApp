package com.kishan.pmapp.feature_auth.presentation.register

sealed class RegisterEvent{
    data class EnteredEmail(val email:String): RegisterEvent()
    data class EnteredPassword(val password:String): RegisterEvent()
    data class EnteredUsername(val username:String): RegisterEvent()
    data object TogglePasswordVisibility: RegisterEvent()
    data object Register: RegisterEvent()
}