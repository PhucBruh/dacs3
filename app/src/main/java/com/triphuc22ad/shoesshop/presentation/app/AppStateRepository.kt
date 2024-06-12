package com.triphuc22ad.shoesshop.presentation.app

import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.domain.model.Product
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

    fun clearCart() {
        _appUiState.value = _appUiState.value.copy(cartItems = emptyList())
    }

    fun isEmptyCart(): Boolean {
        return _appUiState.value.cartItems.isEmpty()
    }

    fun addNewCartItem(cart: CartItem) {
        val updatedCart =
            _appUiState.value.cartItems.toMutableList()
        updatedCart.add(cart)
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCart.toList())
    }

    fun deleteCartItem(productId: Int) {
        val updatedCartItems = _appUiState.value.cartItems.filter { it.productId != productId }
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCartItems)
    }

    fun increaseQuantityCartItem(productId: Int) {
        val updatedCartItems = _appUiState.value.cartItems.map { cartItem ->
            if (cartItem.productId == productId) {
                cartItem.copy(quantity = cartItem.quantity + 1)
            } else {
                cartItem
            }
        }
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCartItems)
    }

    fun decreaseQuantityCartItem(productId: Int) {
        val updatedCartItems = _appUiState.value.cartItems.map { cartItem ->
            if (cartItem.productId == productId && cartItem.quantity > 1) {
                cartItem.copy(quantity = cartItem.quantity - 1)
            } else {
                cartItem
            }
        }
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCartItems)
    }

    fun updateCartItemPrice(product: Product) {
        val updatedCart = _appUiState.value.cartItems.map { currentItem ->
            if (currentItem.productId == product.id) {
                currentItem.copy(
                    productName = product.name,
                    productImg = product.mainImg,
                    price = product.price,
                    promotionPrice = product.promotionPrice,
                )
            } else {
                currentItem
            }
        }
        _appUiState.value = _appUiState.value.copy(cartItems = updatedCart.toList())
    }

    fun checkInCart(productId: Int): Boolean {
        return _appUiState.value.cartItems.any { it.productId == productId }
    }
}