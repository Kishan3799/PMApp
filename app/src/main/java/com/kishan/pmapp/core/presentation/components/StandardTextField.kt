package com.kishan.pmapp.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kishan.pmapp.R
import com.kishan.pmapp.core.utils.TestTags

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text:String = "",
    hint:String = "",
    maxLength:Int = 400,
    error:String = "",
    singleLine:Boolean = true,
    maxLines:Int = 1,
    leadingIcon: ImageVector? = null,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    focusRequester: FocusRequester = FocusRequester()
) {
   Column(
       modifier = Modifier
           .fillMaxWidth()
           .then(modifier)
   )
   {
       OutlinedTextField(
           value = text,
           onValueChange = {
               if(it.length <= maxLength){
                   onValueChange(it)
               }
           },
           maxLines = maxLines,
           placeholder = {
               Text(text = hint)
           },
           isError = error != "",
           keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
           ),
           visualTransformation = if(!isPasswordVisible && isPasswordToggleDisplayed){
               PasswordVisualTransformation()
           }else{
               VisualTransformation.None
           },
           singleLine = singleLine,
           leadingIcon = if(leadingIcon != null){
               val icon: @Composable () -> Unit = {
                   Icon(imageVector = leadingIcon, contentDescription = null)
               }
               icon
           }else  null,
           trailingIcon = if(isPasswordToggleDisplayed){
               val icon: @Composable () -> Unit = {
                    IconButton(onClick = {
                        onPasswordToggleClick(!isPasswordVisible)
                    },
                        modifier = Modifier
                            .semantics {
                                testTag = TestTags.PASSWORD_TOGGLE
                            }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
                            } else {
                                ImageVector.vectorResource(id = R.drawable.baseline_visibility_24)
                            },
                            tint = Color.Black,
                            contentDescription = if (isPasswordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            }
                        )
                    }
               }
               icon
           }else null,
           modifier = Modifier
               .fillMaxWidth()
               .semantics {
                   testTag = TestTags.STANDARD_TEXT_FIELD
               }
               .focusRequester(focusRequester = focusRequester)

       )
       if(error.isNotEmpty()) {
           Text(
               text = error,
               color = Color.Red,
               modifier = Modifier
           )
       }
   }
}

