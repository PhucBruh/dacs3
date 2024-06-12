package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.app.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.publish
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
    private val inventoryService: InventoryService,
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

            ProductDetailEvent.AddToCart -> {
                if (_state.value.selectedColor.id != 0 &&
                    _state.value.selectedSize.id != 0
                ) {
                    viewModelScope.launch {
                        val productResponse =
                            inventoryService.getProductByInventoryInfo(
                                productId = _state.value.product.id,
                                colorId = _state.value.selectedColor.id,
                                sizeId = _state.value.selectedSize.id
                            );
                        println(productResponse)
                        if (productResponse.isSuccessful) {
                            val product = productResponse.body()?.data
                            if (product?.status.equals("ACTIVE")) {
                                appStateRepository.addNewCartItem(
                                    CartItem(
                                        productId = _state.value.product.id,
                                        productImg = _state.value.product.mainImg,
                                        productName = _state.value.product.name,
                                        price = 0.0,
                                        promotionPrice = 0.0,
                                        color = _state.value.selectedColor,
                                        size = _state.value.selectedSize,
                                        quantity = _state.value.quantity
                                    )
                                )
                                appStateRepository.updateNotify("add to cart success")
                                _state.value = _state.value.copy(
                                    isInCart = true
                                )
                            } else {
                                appStateRepository.updateNotify("This product now is inactive")
                            }
                        } else if (productResponse.code() == 404) {
                            appStateRepository.updateNotify("This product with color '${_state.value.selectedColor.name}' and size '${_state.value.selectedSize.size}' not found in inventory")
                        }
                    }
                } else {
                    appStateRepository.updateNotify("please select both sizes and product")
                }
            }
        }
    }
}