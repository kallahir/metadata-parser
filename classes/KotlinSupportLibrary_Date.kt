package br.com.edsilfer.kotlin_support.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String): String {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}
