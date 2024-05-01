package com.triphuc22ad.shoesshop.presentation.cart

import androidx.compose.ui.graphics.Color
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.CartItem

data class CartUiState(
    val items: List<CartItem> = cartItems,
    val deleteOption: CartItem? = null,
    val shippingAddress: String? = null,
    val shippingType: String? = null,
    val promo: String? = null,
)

private val cartItems = listOf(
    CartItem(
        name = "Curry 6",
        price = 105,
        quantity = 1,
        color = Pair("Black", Color.Black),
        imageId = R.drawable.curry_6,
        size = 42
    ),
    CartItem(
        name = "KD 15",
        price = 110,
        quantity = 1,
        color = Pair("Yellow", Color.Yellow.copy(0.6f)),
        imageId = R.drawable.kd_15,
        size = 45
    )
)