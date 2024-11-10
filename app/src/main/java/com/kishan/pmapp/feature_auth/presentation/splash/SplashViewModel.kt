package com.kishan.pmapp.feature_auth.presentation.splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.core.utils.ConfirmMasterPasswordScreen
import com.kishan.pmapp.core.utils.LoginScreen
import com.kishan.pmapp.core.utils.MasterPasswordScreen
import com.kishan.pmapp.core.utils.Resource
import com.kishan.pmapp.feature_auth.data.remote.response.User
import com.kishan.pmapp.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when (val result = authenticateUseCase()) {

                is Resource.Success -> {
                    Log.d("SplashViewModel", "User is authenticated ${result.data}")
                    if(result.data?.hasMasterPassword == true){
                        _eventFlow.emit(
                            UiEvent.Navigate(route = ConfirmMasterPasswordScreen)
                        )
                    }else{
                        _eventFlow.emit(
                            UiEvent.Navigate(route = MasterPasswordScreen)
                        )
                    }
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(route = LoginScreen)
                    )
                }
            }
        }
        }
    }


