package com.kishan.pmapp.feature_auth.presentation.confirmMasterPassword

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kishan.pmapp.R
import com.kishan.pmapp.core.presentation.components.StandardTextField
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.feature_auth.presentation.masterpassword.SetMasterPasswordEvent
import com.kishan.pmapp.feature_auth.util.AuthError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConfirmMasterPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ConfirmMasterPasswordViewModel = hiltViewModel(),
    onConfirmMasterPassword : () -> Unit = {}
) {
    val confirmMasterPasswordState = viewModel.confirmMasterPassword.value
    val state = viewModel.confirmMasterPasswordState.value

    val statusBarHeight = with(LocalDensity.current){
        WindowInsets.statusBars.getTop(this).toDp()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.OnConfirmMasterPassword -> {
                    onConfirmMasterPassword()
                }
                else -> {}
            }
        }
    }

    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(top = statusBarHeight),
    ){
        Box (modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp, horizontal = 16.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),
        ){
            Text(
                modifier = modifier.padding(horizontal = 12.dp, vertical = 16.dp),
                textAlign = TextAlign.Start,
                text = "Current Login user email : ${viewModel.currentUserEmail.value}",
                style = TextStyle(fontSize = 20.sp),
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(all = 16.dp),
        ) {
            Text(text = "Confirm your master password" ,style = TextStyle(
                fontSize = 20.sp,
            ))
            Spacer(modifier = modifier.height(10.dp))
            StandardTextField(
                text = confirmMasterPasswordState.text,
                onValueChange = {
                    viewModel.onEvent(ConfirmMasterPasswordEvent.EnterConfirmMasterPassword(it))
                },
                hint = stringResource(R.string.enter_your_master_password),
                error = when(confirmMasterPasswordState.error){
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InputToShort -> stringResource(id = R.string.error_input_too_short, 6)
                    else -> ""
                },
                keyboardType = KeyboardType.Password,
                isPasswordVisible = confirmMasterPasswordState.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(ConfirmMasterPasswordEvent.TogglePasswordVisibility)
                }
            )
            Spacer(modifier = modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.onEvent(ConfirmMasterPasswordEvent.ConfirmMasterPassword)
                },
                modifier = modifier.align(Alignment.End),
                enabled = !state.isLoading
            ) {
                Text(text = stringResource(R.string.submit), fontSize = 16.sp)
            }
            if(state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

    }
}