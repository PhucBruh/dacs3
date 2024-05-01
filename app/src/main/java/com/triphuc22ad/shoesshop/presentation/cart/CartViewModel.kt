package com.triphuc22ad.shoesshop.presentation.cart

import com.triphuc22ad.shoesshop.core.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : CoreViewModel<CartUiState, Unit, CartAction, Unit>(
    initialState = CartUiState(),
    environment = Unit
) {

    override fun dispatch(action: CartAction) {
        when (action) {
            CartAction.CheckOut -> TODO()
            is CartAction.DecreaseQuantity -> TODO()
            is CartAction.DeleteItem -> TODO()
            is CartAction.IncreaseQuantity -> TODO()
            is CartAction.ToggleDelete -> {
                setState { copy(deleteOption = action.item) }
            }
        }
    }
}