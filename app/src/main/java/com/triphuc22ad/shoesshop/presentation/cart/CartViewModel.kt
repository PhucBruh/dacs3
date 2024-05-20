package com.triphuc22ad.shoesshop.presentation.cart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<CartUiState>(CartUiState())
    val state: StateFlow<CartUiState> = _state.asStateFlow()

    fun onEvent(event: CartEvent) {
        when (event) {
            CartEvent.CheckOut -> TODO()
            is CartEvent.DecreaseQuantity -> TODO()
            is CartEvent.DeleteItem -> TODO()
            is CartEvent.IncreaseQuantity -> TODO()
            is CartEvent.ToggleDelete -> TODO()
        }
    }

    private fun checkOut() {
    }
}