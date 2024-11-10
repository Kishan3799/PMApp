package com.kishan.pmapp.core.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.core.utils.AddPasswordEntryScreen
import com.kishan.pmapp.core.utils.ConfirmMasterPasswordScreen
import com.kishan.pmapp.core.utils.HomeScreen
import com.kishan.pmapp.core.utils.LoginScreen
import com.kishan.pmapp.core.utils.MasterPasswordScreen
import com.kishan.pmapp.core.utils.RegisterScreen
import com.kishan.pmapp.core.utils.ShowPasswordScreen
import com.kishan.pmapp.core.utils.SplashScreen
import com.kishan.pmapp.core.utils.UpdatePasswordEntryScreen
import com.kishan.pmapp.feature_auth.presentation.login.LoginScreen
import com.kishan.pmapp.feature_auth.presentation.masterpassword.MasterPasswordScreen
import com.kishan.pmapp.feature_auth.presentation.register.RegisterScreen
import com.kishan.pmapp.feature_auth.presentation.splash.SplashScreen
import com.kishan.pmapp.feature_auth.presentation.confirmMasterPassword.ConfirmMasterPasswordScreen
import com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry.AddPasswordEntryScreen
import com.kishan.pmapp.feature_password_entry.presentation.home.HomeScreen
import com.kishan.pmapp.feature_password_entry.presentation.home.ShowPasswordScreen
import com.kishan.pmapp.feature_password_entry.presentation.updatePasswordEntry.UpdatePasswordEntryScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
){
    NavHost(
        navController = navController,
        startDestination = SplashScreen
    ){
        composable<SplashScreen> {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate
            )
        }
        composable<LoginScreen> {
            LoginScreen(
                onNavigateToSignup = { navController.navigate(route = RegisterScreen) },
                onLogin = {
                    navController.popBackStack(
                        route = LoginScreen,
                        inclusive = true
                    )
                    navController.navigate(route = MasterPasswordScreen)
                },
                scaffoldState = scaffoldState
            )
        }
        composable<RegisterScreen> {
            RegisterScreen(
                onPopBackStack = navController::popBackStack,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable<MasterPasswordScreen> {
            MasterPasswordScreen(
                onMasterPassword = {
                    navController.navigate(route = ConfirmMasterPasswordScreen)
                }
            )
        }
        composable<ConfirmMasterPasswordScreen> {
            ConfirmMasterPasswordScreen(
                onConfirmMasterPassword = {
                    navController.navigate(route = HomeScreen)
                }
            )
        }
        composable<HomeScreen> {
            HomeScreen(
                onNavigateToAddPasswordEntry = {
                    navController.navigate(route = AddPasswordEntryScreen)
                },
                onNavigateToShowPassword = { passwordEntry ->
                    navController.navigate(route = ShowPasswordScreen(
                        id = passwordEntry.id,
                        websiteName = passwordEntry.websiteName,
                        websiteUrl = passwordEntry.websiteUrl,
                        siteUsername = passwordEntry.siteUsername,
                        siteEmail = passwordEntry.siteEmail,
                        sitePassword = passwordEntry.sitePassword,
                        siteNote = passwordEntry.siteNote,
                        userId = passwordEntry.userId,
                        createdAt = passwordEntry.createdAt,
                        updatedAt = passwordEntry.updatedAt
                    ))
                },
                onNavigateToEditPassword = { passwordEntry ->
                    navController.navigate(route = UpdatePasswordEntryScreen(
                        id = passwordEntry.id,
                        websiteName = passwordEntry.websiteName,
                        websiteUrl = passwordEntry.websiteUrl,
                        siteUsername = passwordEntry.siteUsername,
                        siteEmail = passwordEntry.siteEmail,
                        sitePassword = passwordEntry.sitePassword,
                        siteNote = passwordEntry.siteNote,
                        userId = passwordEntry.userId,
                        createdAt = passwordEntry.createdAt,
                        updatedAt = passwordEntry.updatedAt
                    ))
                },
                onLogout = {
                    navController.navigate(route = LoginScreen)
                }

            )
        }
        composable<AddPasswordEntryScreen> {
            AddPasswordEntryScreen()
        }
        composable<ShowPasswordScreen> {
            val passwordEntry : ShowPasswordScreen = it.toRoute()
            ShowPasswordScreen(
                passwordEntry = PasswordEntry(
                    websiteName = passwordEntry.websiteName,
                    websiteUrl = passwordEntry.websiteUrl,
                    siteUsername = passwordEntry.siteUsername,
                    siteEmail = passwordEntry.siteEmail,
                    sitePassword = passwordEntry.sitePassword,
                    siteNote = passwordEntry.siteNote,
                    id = passwordEntry.id,
                    userId = passwordEntry.userId,
                    updatedAt = passwordEntry.createdAt,
                    createdAt = passwordEntry.updatedAt
                )
            )
        }
        composable<UpdatePasswordEntryScreen> {
            val passwordEntry : ShowPasswordScreen = it.toRoute()
            UpdatePasswordEntryScreen(
                passwordEntry = PasswordEntry(
                    websiteName = passwordEntry.websiteName,
                    websiteUrl = passwordEntry.websiteUrl,
                    siteUsername = passwordEntry.siteUsername,
                    siteEmail = passwordEntry.siteEmail,
                    sitePassword = passwordEntry.sitePassword,
                    siteNote = passwordEntry.siteNote,
                    id = passwordEntry.id,
                    userId = passwordEntry.userId,
                    updatedAt = passwordEntry.createdAt,
                    createdAt = passwordEntry.updatedAt
                )
            )
        }

    }
}