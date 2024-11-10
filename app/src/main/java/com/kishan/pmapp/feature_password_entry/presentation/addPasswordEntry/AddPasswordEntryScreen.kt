package com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kishan.pmapp.core.presentation.components.StandardTextField
import com.kishan.pmapp.core.presentation.util.UiEvent
import com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry.component.GeneratePasswordDialog
import com.kishan.pmapp.feature_password_entry.presentation.util.PasswordEntryError
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPasswordEntryScreen(
    modifier: Modifier = Modifier,
    viewModel : AddPasswordViewModel = hiltViewModel()
) {
    val websiteNameState = viewModel.websiteNameState.value
    val websiteUrlState = viewModel.websiteUrlState.value
    val siteUserNameState = viewModel.siteUserNameState.value
    val siteEmailState = viewModel.siteEmailState.value
    val sitePasswordState = viewModel.sitePasswordState.value
    val siteNoteState = viewModel.siteNoteState.value
    val addPasswordState = viewModel.addPasswordState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                UiEvent.onSavePassword -> {
                    Toast.makeText(context, "Password Saved Successfully", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    Box (modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.shadow(5.dp),
                    colors = topAppBarColors(
                        containerColor = Color(0xFFFAFA33),
                        titleContentColor = Color(0xFF006BFF)
                    ),
                    title = {
                        Text(
                            "Add Password", style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 3.sp
                            )
                        )
                    }
                )
            },
        ) { innerPadding ->
            Box (modifier = modifier
                .padding(innerPadding)
                .fillMaxHeight()){
                //Form
                Column(
                    modifier = modifier
                        .padding(16.dp)
                ) {
                    StandardTextField(
                        text = websiteNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddPasswordEvent.EnteredWebsiteName(it))
                        },
                        keyboardType = KeyboardType.Text,
                        error = when(websiteNameState.error){
                            is PasswordEntryError.EntryFieldEmpty -> "This field cannot be empty"
                            else -> ""
                        },
                        hint = "Website Name"
                    )
                    Spacer(modifier = modifier.height(10.dp))

                    StandardTextField(
                        text = websiteUrlState.text,
                        onValueChange = { viewModel.onEvent(AddPasswordEvent.EnteredWebsiteUrl(it)) },
                        keyboardType = KeyboardType.Text,
                        error = when(websiteUrlState.error){
                            is PasswordEntryError.EntryFieldEmpty -> "This field cannot be empty"
                            else -> ""
                        },
                        hint = "Website URL"
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    StandardTextField(
                        text = siteUserNameState.text,
                        onValueChange = { viewModel.onEvent(AddPasswordEvent.EnteredSiteUserName(it)) },
                        keyboardType = KeyboardType.Text,
                        error = when(siteUserNameState.error){
                            is PasswordEntryError.EntryFieldEmpty -> "This field cannot be empty"
                            else -> ""
                        },
                        hint = "Site User Name"
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    StandardTextField(
                        text = siteEmailState.text,
                        onValueChange = { viewModel.onEvent(AddPasswordEvent.EnteredSiteEmail(it)) },
                        keyboardType = KeyboardType.Email,
                        error = when(siteEmailState.error){
                            is PasswordEntryError.EntryFieldEmpty -> "This field cannot be empty"
                            else -> ""
                        },
                        hint = "Site Email"
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    StandardTextField(
                        text = sitePasswordState.text,
                        onValueChange = { viewModel.onEvent(AddPasswordEvent.EnteredSitePassword(it)) },
                        keyboardType = KeyboardType.Password,
                        error = when(sitePasswordState.error){
                            is PasswordEntryError.EntryFieldEmpty -> "This field cannot be empty"
                            is PasswordEntryError.PasswordIsWeak -> "Password is weak"
                            is PasswordEntryError.PasswordIsShort -> "Password is too short"
                            else -> ""
                        },

                        hint = "Site Password",
                        isPasswordVisible = addPasswordState.isPasswordVisible,
                        onPasswordToggleClick = {
                            viewModel.onEvent(AddPasswordEvent.TogglePasswordVisibility)
                        }
                    )
                    TextButton(onClick = {
                        viewModel.onEvent(AddPasswordEvent.OpenDialog)
                    }, modifier = Modifier.align(Alignment.End)) {
                        Text(
                            text = "Generate Password",
                            fontSize = 20.sp,
                            color = Color(0xFF006BFF)
                        )
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    StandardTextField(
                        text = siteNoteState.text,
                        onValueChange = {viewModel.onEvent(AddPasswordEvent.EnteredSiteNote(it))},
                        keyboardType = KeyboardType.Text,
                        hint = "Write note here",
                        singleLine = false,
                        maxLines = 10,
                    )
                }
                Button(
                    onClick = { viewModel.onEvent(AddPasswordEvent.SaveEntry) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    colors = buttonColors(
                        containerColor = Color(0xFFFAFA33),
                        contentColor = Color(0xFF006BFF)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp,
                        disabledElevation = 0.dp
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    if(addPasswordState.isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.padding(all = 10.dp)
                        )
                    }else{
                        Text(
                            modifier = Modifier.padding(all = 10.dp),
                            text = "Add",
                            fontSize = 20.sp,
                            color = Color(0xFF006BFF)
                        )
                    }

                }
                if(viewModel.addPasswordState.value.openDialog){
                    GeneratePasswordDialog(
                        onDismiss = { viewModel.onEvent(AddPasswordEvent.CloseDialog) },
                        onGeneratePassword = { viewModel.onEvent(AddPasswordEvent.EnteredSitePassword(it)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    AddPasswordEntryScreen()
}