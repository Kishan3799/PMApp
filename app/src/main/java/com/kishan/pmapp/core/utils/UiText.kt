package com.kishan.pmapp.core.utils

import androidx.annotation.StringRes
import com.kishan.pmapp.R

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val resId: Int): UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(R.string.error_unknown)
        }
    }

}