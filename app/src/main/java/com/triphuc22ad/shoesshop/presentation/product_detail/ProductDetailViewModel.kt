package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.domain.model.ProductDetail
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val productId: Int =
        savedStateHandle["productId"] ?: 0

    private val _state = MutableStateFlow(ProductDetailUiState())
    val state: MutableStateFlow<ProductDetailUiState> = _state

    init {
        viewModelScope.launch {
            val response = productService.getProductById(productId);
            if (response.isSuccessful) {
                val product = response.body()!!.data

                if (product != null) {
                    _state.value = _state.value.copy(
                        product = product
                    )
                }
            } else {
                appStateRepository.updateNotify("product not found")
            }
            if (appStateRepository.checkInCart(productId)) {
                _state.value = _state.value.copy(isInCart = true)
            }
        }
    }


    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.ChangeColor -> {
                _state.value = _state.value.copy(
                    selectedColorId = event.colorId
                )
            }

            is ProductDetailEvent.ChangeSize -> {
                _state.value = _state.value.copy(
                    selectedSizeId = event.sizeId
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

            ProductDetailEvent.AddToCart -> {
                if (_state.value.selectedColorId != 0 &&
                    _state.value.selectedSizeId != 0
                ) {
                    appStateRepository.addNewCartItem(
                        productId = _state.value.product.id,
                        colorId = _state.value.selectedColorId,
                        sizeId = _state.value.selectedSizeId,
                        quantity = _state.value.quantity,
                    )
                    appStateRepository.updateNotify("add to cart success")
                    _state.value = _state.value.copy(
                        isInCart = true
                    )
                } else {
                    appStateRepository.updateNotify("please select both sizes and product")
                }
            }
        }
    }
}