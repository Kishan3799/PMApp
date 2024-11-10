package com.kishan.pmapp.feature_password_entry.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kishan.pmapp.R
import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.feature_password_entry.presentation.home.components.PasswordEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToAddPasswordEntry: () -> Unit = {},
    onNavigateToShowPassword: (PasswordEntry) -> Unit = {},
    onNavigateToEditPassword: (PasswordEntry) -> Unit = {},
    onLogout: () -> Unit = {},
    viewModel: FetchPasswordViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box (
        modifier = modifier.fillMaxSize(),
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.shadow(5.dp),
                    colors = topAppBarColors(
                        containerColor = Color(0xFFFAFA33),
                        titleContentColor = Color(0xFF006BFF)
                    ),
                    title = {
                        Text(stringResource(id = R.string.app_name), style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 3.sp
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.logout()
                            onLogout()
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_logout_24), contentDescription = "Logout")
                        }
                    }
                )
            },

            floatingActionButton = {
                LargeFloatingActionButton(
                    onClick = onNavigateToAddPasswordEntry,
                    containerColor = Color(0xFFFAFA33),
                    shape = CircleShape,
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color(0xFF006BFF))
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if(state.isLoading){
                    CircularProgressIndicator()
                }else{
                    LazyColumn(
                        modifier = modifier.weight(1f),
                    ) {
                        items(state.passwordEntries) { passwordEntry ->

                            Box (modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                                PasswordEntry(
                                    passwordEntry = passwordEntry,
                                    navigateToShowPassword = {
                                        onNavigateToShowPassword(it)
                                    },
                                    navigateToUpdatePassword = {
                                        onNavigateToEditPassword(it)
                                    },
                                    deletePasswordEntry = {
                                        viewModel.deletePasswordEntry(it)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    HomeScreen()
}