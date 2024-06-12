package com.triphuc22ad.shoesshop.presentation.cart

import com.triphuc22ad.shoesshop.domain.model.CartItem

sealed class CartEvent {
    data class CheckOut(val navigateToCheckOut: () -> Unit) : CartEvent()
    data object CreateOrder : CartEvent()
    data class ToggleDelete(val item: CartItem? = null) : CartEvent()
    data class DeleteItem(val productId: Int) : CartEvent()
    data class IncreaseQuantity(val productId: Int) : CartEvent()
    data class DecreaseQuantity(val productId: Int) : CartEvent()
}