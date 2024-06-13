package com.triphuc22ad.shoesshop.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.data.service.OrderDetailRequest
import com.triphuc22ad.shoesshop.data.service.OrderRequest
import com.triphuc22ad.shoesshop.data.service.UserService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.cart.CartEvent.CheckOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val inventoryService: InventoryService,
    private val userService: UserService,
) : ViewModel() {

    private val _state = MutableStateFlow<CartUiState>(CartUiState())
    val state: StateFlow<CartUiState> = _state.asStateFlow()

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.DecreaseQuantity -> {
                appStateRepository.decreaseQuantityCartItem(event.productId)
            }

            is CartEvent.IncreaseQuantity -> {
                appStateRepository.increaseQuantityCartItem(event.productId)
            }

            is CartEvent.DeleteItem -> {
                appStateRepository.deleteCartItem(event.productId)
            }

            is CartEvent.ToggleDelete -> TODO()
            is CheckOut -> {
                if (appStateRepository.isEmptyCart()) {
                    appStateRepository.updateNotify("Cart now is empty")
                } else {
                    event.navigateToCheckOut()
                }
            }

            CartEvent.CreateOrder -> createOrder()
        }
    }

    private fun createOrder() {
        if (_state.value.shippingAddress.isEmpty()) {
            appStateRepository.updateNotify("Please add shipping address")
        } else {
            val orderDetailsRequest = appStateRepository.appUiState.value.cartItems.map {
                OrderDetailRequest(
                    productId = it.productId,
                    colorId = it.color.id,
                    sizeId = it.size.id,
                    quantity = it.quantity,
                )
            }
            viewModelScope.launch {
                val response = userService.createOrder(
                    OrderRequest(
                        shippingAddress = _state.value.shippingAddress,
                        description = _state.value.description,
                        detail = orderDetailsRequest
                    )
                )
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse!!.success) {
                        appStateRepository.updateNotify("Create order success")
                        _state.value = state.value.copy(
                            isCreatedOrder = true
                        )
                    } else {
                        apiResponse.message?.let { appStateRepository.updateNotify(it) }
                    }
                } else {
                    appStateRepository.updateNotify("Create order failed")
                }
            }
        }
    }

    fun validateCart() {
        viewModelScope.launch {
            val cart = appStateRepository.appUiState.value.cartItems
            var notify = false
            if (!cart.isEmpty()) {
                cart.forEach {
                    val productResponse =
                        inventoryService.getProductByInventoryInfo(
                            productId = it.productId,
                            colorId = it.color.id,
                            sizeId = it.size.id
                        );
                    if (productResponse.isSuccessful) {
                        val product = productResponse.body()?.data
                        product?.let { it1 ->
                            if (it1.status == "ACTIVE") {
                                appStateRepository.updateCartItemPrice(it1)
                            } else {
                                appStateRepository.deleteCartItem(it.productId)
                                notify = true
                            }
                        }
                    } else if (productResponse.code() == 404) {
                        appStateRepository.deleteCartItem(it.productId)
                        notify = true
                    }
                }
            }
            if (notify) {
                appStateRepository.updateNotify("Update cart and delete item that not found in inventory or inactive")
            }
        }

    }
}