package com.nitin.core.extensions

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*


inline fun String?.ifNotBlank(block: String.() -> Unit)  {
    if (this?.isNotBlank() == true)
        block(this)
}
inline fun CharSequence?.ifNullOrBlank(defaultValue: () -> CharSequence): CharSequence = if (isNullOrBlank()) defaultValue() else this
fun String.getSha256Hash(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
fun Double?.toTimeString(format: String = "hh:mm aa"): String {
    val timestamp = this ?: return ""
    return try {
        SimpleDateFormat(format, Locale.getDefault()).format(timestamp * 1000) ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}


fun String.removeInBetweenSpace() = replace("\\s".toRegex(), "")