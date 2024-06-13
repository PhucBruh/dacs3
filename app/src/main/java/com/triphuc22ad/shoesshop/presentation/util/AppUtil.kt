package com.triphuc22ad.shoesshop.presentation.util

fun limitText(text: String, maxLength: Int): String {
    val truncatedText = if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
    return truncatedText
}