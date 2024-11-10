package com.kishan.pmapp.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnterEmail(val email:String) : LoginEvent()
    data class EnterPassword(val password:String) : LoginEvent()
    data object TogglePasswordVisibility : LoginEvent()
    data object Login : LoginEvent()

}