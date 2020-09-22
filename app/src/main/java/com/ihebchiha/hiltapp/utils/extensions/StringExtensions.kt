package com.ihebchiha.hiltapp.utils.extensions

import android.util.Patterns
import java.util.regex.Pattern


const val EMAIL_INPUT_TYPE = 0
const val ONLY_NUMERIC_INPUT_TYPE = 1
const val PHONE_NUMBER_INPUT_TYPE = 2
val ONLY_NUMERIC_PATTERN = Pattern.compile("/[1-9]/g")
val HTML_TAG_PATTERN: Pattern = Pattern.compile("<[^>]*>")
val SPECIAL_CHARS_PATTERN: Pattern = Pattern.compile("[\$&+:;=\\\\1-9@#|/<>^*()%-]")
fun String.removeUnwantedChars(pattern: Pattern): String{
    return this.replace(pattern.toRegex(), "")
}

fun String.checkInputValidity(inputType: Int): Boolean{
    var verification = false
    if (this.isNotEmpty()) {
        when (inputType) {
            EMAIL_INPUT_TYPE -> {
                verification = Patterns.EMAIL_ADDRESS.matcher(this).matches()
            }
            ONLY_NUMERIC_INPUT_TYPE -> {
                verification = ONLY_NUMERIC_PATTERN.matcher(this).matches()
            }
            PHONE_NUMBER_INPUT_TYPE -> {
                verification = Patterns.PHONE.matcher(this).matches()
            }
        }
    }
    return verification
}