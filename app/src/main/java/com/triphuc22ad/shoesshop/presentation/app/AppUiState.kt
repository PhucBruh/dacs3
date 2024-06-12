package com.triphuc22ad.shoesshop.presentation.app

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Size
import com.triphuc22ad.shoesshop.domain.model.User

data class AppUiState(
    val isLoggedIn: Boolean = false,
    val showBottomBar: Boolean = false,

    val isDarkMode: Boolean = false,
    val notify: NotifyMessage = NotifyMessage(""),
    val user: User? = null,
    val productFilter: ProductFilter = ProductFilter(),
    val cartItems: List<CartItem> = emptyList(),
)

data class NotifyMessage(
    val value: String,
    val id: Long = System.currentTimeMillis(),
)

data class ProductFilter(
    val query: String = "",
    val orderBy: String = "",
    val brandId: Int = 0,
    val minPrice: Double = 0.0,
    val maxPrice: Double = 0.0,
)

data class CartItem(
    val productId: Int,
    val productName: String,
    val productImg: String,
    val color: Color,
    val size: Size,
    val quantity: Int,
    var price: Double,
    var promotionPrice: Double,
)