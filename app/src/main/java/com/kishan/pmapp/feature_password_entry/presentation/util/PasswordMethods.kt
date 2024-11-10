package com.kishan.pmapp.feature_password_entry.presentation.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import kotlin.random.Random

fun generatedPassword(
    length: Int,
    includeSymbols: Boolean,
    includeNumbers: Boolean,
    includeUpperCases: Boolean
): String{
    val lowercaseChars = "abcdefghijklmnopqrstuvwxyz"
    val uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers = "0123456789"
    val symbols = "!@#\$%^&*()-_=+{}[]<>?"

    var allowedChars = lowercaseChars
    if (includeUpperCases) allowedChars += uppercaseChars
    if (includeNumbers) allowedChars += numbers
    if (includeSymbols) allowedChars += symbols

    return (1..length)
        .map { allowedChars[Random.nextInt(allowedChars.length)] }
        .joinToString("")
}

fun copyToClipBoard(context: Context, text: String){
    val clipboard = getSystemService(context, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("password", text)
    clipboard?.setPrimaryClip(clip)
}