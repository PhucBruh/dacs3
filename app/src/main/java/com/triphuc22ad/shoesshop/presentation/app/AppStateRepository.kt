package com.triphuc22ad.shoesshop.presentation.app

import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStateRepository @Inject constructor(
    private val productService: ProductService,
) {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    fun resetAppState() {
        _appUiState.value = AppUiState()
    }

    fun updateUserInfo(userInfo: UserInfoResponse) {
        val role = if (userInfo.roles.contains("ROLE_ADMIN")) "ADMIN" else "USER"
        _appUiState.value = _appUiState.value.copy(
            user = User(
                firstName = userInfo.firstName,
                lastName = userInfo.lastName,
                email = userInfo.email,
                phone = userInfo.phone,
                role = role
            )
        )
    }

    fun showBottomBar(isShowed: Boolean) {
        _appUiState.value = _appUiState.value.copy(showBottomBar = isShowed)
    }

    fun updateNotify(message: String) {
        _appUiState.value = _appUiState.value.copy(notify = NotifyMessage(message))
    }

    fun addNewCartItem(cart: CartItem) {
        val updatedCart =
            _appUiState.value.cartItems.toMutableList()
        updatedCart.add(cart)
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCart.toList())
    }

    fun deleteCartItem(productId: Int) {

    }

    fun inCreaseQuantityCartItem() {
    }

    suspend fun validateCartItemAndUpdatePrice() {
        val cart = _appUiState.value.copy().cartItems
        cart.forEach { item ->
            val response = productService.getProductById(item.productId)
            if (response.isSuccessful) {
                val productPrice = response.body()?.data
                item.price = productPrice?.price ?: 0.0
                item.promotionPrice = productPrice?.promotionalPrice ?: 0.0
            } else if (response.code() == 404) {
                updateNotify("The product name '$item.productName' is not found, app auto remove it from cart.")
            }
        }
        _appUiState.value = _appUiState.value.copy(cartItems = cart)
    }

    fun checkInCart(productId: Int): Boolean {
        return _appUiState.value.cartItems.any { it.productId == productId }
    }
}