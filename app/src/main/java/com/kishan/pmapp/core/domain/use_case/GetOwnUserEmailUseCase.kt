package com.kishan.pmapp.core.domain.use_case

import android.content.SharedPreferences
import com.kishan.pmapp.core.utils.Constants

class GetOwnUserEmailUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return (sharedPreferences.getString(Constants.KEY_USER_EMAIL, "") ?: "")
    }

}