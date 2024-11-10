package com.kishan.pmapp.feature_password_entry.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kishan.pmapp.R
import com.kishan.pmapp.core.data.PasswordEntry
import com.kishan.pmapp.feature_password_entry.presentation.util.copyToClipBoard

@Composable
fun PasswordEntry(
    passwordEntry: PasswordEntry,
    modifier: Modifier = Modifier,
    navigateToShowPassword: (PasswordEntry) -> Unit = {},
    navigateToUpdatePassword: (PasswordEntry) -> Unit = {},
    deletePasswordEntry: (String) -> Unit = {}
) {
    val context = LocalContext.current
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { navigateToShowPassword(passwordEntry) }, elevation = cardElevation(4.dp)) {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)){
            Text(
                text = passwordEntry.siteEmail,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
            Spacer(modifier = modifier.height(8.dp))
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = passwordEntry.sitePassword,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                IconButton(onClick = {
                    copyToClipBoard(context, passwordEntry.sitePassword)
                    Toast.makeText(context, "Password Copied", Toast.LENGTH_SHORT).show()
                }, modifier = modifier.padding(all = 0.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = "Copy Password",
                    )
                }
            }
            Row(modifier = modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.End),) {
                Box (
                    modifier
                        .size(40.dp)
                        .clickable {
                            Toast.makeText(context, "Edit Password", Toast.LENGTH_SHORT).show()
                            navigateToUpdatePassword(passwordEntry)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Password")
                }
                Box(
                    modifier
                        .size(40.dp)
                        .clickable {
                            Toast.makeText(context, "Delete Password", Toast.LENGTH_SHORT).show()
                            deletePasswordEntry(passwordEntry.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Password")
                }
            }

        }
    }
}
