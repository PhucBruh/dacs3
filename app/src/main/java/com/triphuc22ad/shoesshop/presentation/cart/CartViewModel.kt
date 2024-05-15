package com.triphuc22ad.shoesshop.presentation.cart

import com.triphuc22ad.shoesshop.core.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : CoreViewModel<CartUiState, Unit, CartEvent, Unit>(
    initialState = CartUiState(),
    environment = Unit
) {

    override fun dispatch(action: CartEvent) {
        when (action) {
            CartEvent.CheckOut -> TODO()
            is CartEvent.DecreaseQuantity -> TODO()
            is CartEvent.DeleteItem -> TODO()
            is CartEvent.IncreaseQuantity -> TODO()
            is CartEvent.ToggleDelete -> {
                setState { copy(deleteOption = action.item) }
            }
        }
    }
}