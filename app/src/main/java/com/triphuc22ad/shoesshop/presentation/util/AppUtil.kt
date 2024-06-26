package com.triphuc22ad.shoesshop.presentation.util

import androidx.compose.ui.graphics.Color
import java.text.NumberFormat
import java.util.Locale

fun limitText(text: String, maxLength: Int): String {
    val truncatedText = if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
    return truncatedText
}

fun formatPrice(value: Double): String {
    return NumberFormat.getCurrencyInstance(Locale("vi", "VN")).apply {
        maximumFractionDigits = 0
    }.format(value)
}

fun parseColor(value: String): Color {
    return try {
        Color(android.graphics.Color.parseColor(value))
    } catch (e: IllegalArgumentException) {
        Color.Transparent
    }
}