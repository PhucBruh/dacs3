package com.triphuc22ad.shoesshop.presentation.cart

import com.triphuc22ad.shoesshop.domain.model.CartItem

sealed class CartEvent {
    data object CheckOut : CartEvent()
    data class ToggleDelete(val item: CartItem? = null) : CartEvent()
    data class DeleteItem(val item: CartItem) : CartEvent()
    data class IncreaseQuantity(val item: CartItem) : CartEvent()
    data class DecreaseQuantity(val item: CartItem) : CartEvent()
}