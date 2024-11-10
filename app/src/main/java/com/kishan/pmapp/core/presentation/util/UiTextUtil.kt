package com.kishan.pmapp.core.presentation.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kishan.pmapp.core.utils.UiText


@Composable
fun UiText.asString(): String {
    return when(this) {
        is UiText.DynamicString -> this.value
        is UiText.StringResource -> stringResource(id = this.resId)
    }
}

fun UiText.asString(context: Context): String {
    return when(this) {
        is UiText.DynamicString -> this.value
        is UiText.StringResource -> context.getString(this.resId)
    }
}