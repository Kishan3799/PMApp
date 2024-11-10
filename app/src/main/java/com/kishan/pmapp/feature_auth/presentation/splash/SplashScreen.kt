package com.kishan.pmapp.feature_auth.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kishan.pmapp.core.presentation.util.UiEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: ( route: Any ) -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate<*> -> {
                    onPopBackStack()
                    onNavigate(event.route)
                }
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Color.Magenta
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Pass Keeper ", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}



