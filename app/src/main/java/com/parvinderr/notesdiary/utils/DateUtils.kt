package com.parvinderr.notesdiary.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getCurrentDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(this)
}
