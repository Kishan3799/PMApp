package com.kishan.pmapp.feature_password_entry.presentation.home

import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kishan.pmapp.R
import com.kishan.pmapp.core.data.PasswordEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPasswordScreen(
    modifier: Modifier = Modifier,
    passwordEntry: PasswordEntry
) {


    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(5.dp),
                colors = topAppBarColors(
                    containerColor = Color(0xFFFAFA33),
                    titleContentColor = Color(0xFF006BFF)
                ),
                title = {
                    Text(
                        "Password Detail", style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 3.sp
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)){

            Column (modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
            {
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.websiteName,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.websiteUrl,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.siteUsername,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.siteEmail,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.sitePassword,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = passwordEntry.siteNote,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,letterSpacing = 0.1.em,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

            }


        }

    }


}