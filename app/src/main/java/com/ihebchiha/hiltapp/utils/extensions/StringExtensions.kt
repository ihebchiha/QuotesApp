package com.ihebchiha.hiltapp.utils.extensions

import java.util.regex.Pattern

val HTML_TAG_PATTERN: Pattern = Pattern.compile("<[^>]*>")
val SPECIAL_CHARS_PATTERN: Pattern = Pattern.compile("[\$&+:;=\\\\1-9@#|/<>^*()%-]")
fun String.removeUnwantedChars(pattern: Pattern): String{
    return this.replace(pattern.toRegex(), "")
}