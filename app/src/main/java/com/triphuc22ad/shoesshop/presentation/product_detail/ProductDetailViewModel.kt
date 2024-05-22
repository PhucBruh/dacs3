package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        ProductDetailUiState(
            product = Product(

                name = "Product 1",
                description = "new gen nike shoes",
                rating = 4.5f,
                price = 100000.0,
                totalSold = 100,
                brand = "Nike",
                img_url = "",
                isFavorite = false,
                colors = listOf(
                    Pair("Black", "FF0000"),
                    Pair("Blue", "FF0000"),
                ),
                sizes = listOf(41, 42, 43)
            ),
        )
    )
    val state: MutableStateFlow<ProductDetailUiState> = _state

    init {
        _state.value = state.value.copy(
            selectedColor = state.value.product.colors[0].second,
            selectedSize = state.value.product.sizes[0]
        )
    }

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.ChangeColor -> {
                _state.value = _state.value.copy(
                    selectedColor = event.color
                )
            }

            is ProductDetailEvent.ChangeSize -> {
                _state.value = _state.value.copy(
                    selectedSize = event.size
                )
            }

            ProductDetailEvent.DecreaseQuantity -> {
                if (_state.value.quantity > 1) {
                    _state.value = _state.value.copy(
                        quantity = _state.value.quantity - 1
                    )
                }
            }

            ProductDetailEvent.IncreaseQuantity -> {
                _state.value = _state.value.copy(
                    quantity = _state.value.quantity + 1
                )
            }
        }
    }
}