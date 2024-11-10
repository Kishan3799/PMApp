package com.kishan.pmapp.feature_password_entry.presentation.addPasswordEntry.component

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.kishan.pmapp.R
import com.kishan.pmapp.feature_password_entry.presentation.util.copyToClipBoard
import com.kishan.pmapp.feature_password_entry.presentation.util.generatedPassword

@Composable
fun GeneratePasswordDialog(
    modifier: Modifier = Modifier,
    onDismiss:() -> Unit,
    onGeneratePassword:(String)-> Unit
) {
    var passwordLength by remember { mutableIntStateOf(8) }
    var includeSymbols by remember { mutableStateOf(true) }
    var includeNumbers by remember { mutableStateOf(true) }
    var includeUpperCases by remember { mutableStateOf(true) }
    var generatedPassword by remember { mutableStateOf("") }

    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Generate Password")},
        text = {
            Column() {
                // Length Slider
                Text(text = "Password Length: $passwordLength")
                Slider(
                    value = passwordLength.toFloat(),
                    onValueChange = { passwordLength = it.toInt() },
                    valueRange = 8f..32f
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Options
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = includeSymbols, onCheckedChange = { includeSymbols = it })
                    Text("Include Symbols")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = includeNumbers, onCheckedChange = { includeNumbers = it })
                    Text("Include Numbers")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = includeUpperCases, onCheckedChange = { includeUpperCases = it })
                    Text("Include Uppercase Letters")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Display Generated Password
                OutlinedTextField(
                    value = TextFieldValue(generatedPassword),
                    onValueChange = {},
                    label = { Text("Generated Password") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            copyToClipBoard(context, generatedPassword)
                            Toast.makeText(context, "Password copied to clipboard", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_content_copy_24), contentDescription = "Copy Password to Clipboard")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )



            }
        },
        confirmButton = {
            Button(onClick = {
                generatedPassword = generatedPassword(
                    length = passwordLength,
                    includeSymbols = includeSymbols,
                    includeNumbers = includeNumbers,
                    includeUpperCases = includeUpperCases
                )
                onGeneratePassword(generatedPassword)
            }){
                Text("Generate")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

}



@Preview
@Composable
private fun DefaultPreview() {
    GeneratePasswordDialog(onDismiss = {}, onGeneratePassword = {})
}