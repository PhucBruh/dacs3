package com.triphuc22ad.shoesshop.presentation.cart

import com.triphuc22ad.shoesshop.domain.model.CartItem

sealed class CartAction {
    data object CheckOut : CartAction()
    data class ToggleDelete(val item: CartItem? = null) : CartAction()
    data class DeleteItem(val item: CartItem) : CartAction()
    data class IncreaseQuantity(val item: CartItem) : CartAction()
    data class DecreaseQuantity(val item: CartItem) : CartAction()
}