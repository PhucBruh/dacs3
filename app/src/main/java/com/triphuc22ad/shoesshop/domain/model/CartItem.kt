package com.triphuc22ad.shoesshop.domain.model

import androidx.compose.ui.graphics.Color

data class CartItem(
    val name: String,
    val price: Long,
    val quantity: Int,
    val color: Pair<String, Color>,
    val imageId: Int,
    val size: Int,
)