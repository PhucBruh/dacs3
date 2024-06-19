package com.triphuc22ad.shoesshop.presentation.app

import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.User
import com.triphuc22ad.shoesshop.presentation.admin.brand.list.AdminBrandUiState
import com.triphuc22ad.shoesshop.presentation.admin.inventory.list.AdminInventoryUiState
import com.triphuc22ad.shoesshop.presentation.admin.order.list.AdminOrderUiState
import com.triphuc22ad.shoesshop.presentation.admin.product.list.AdminProductUiState
import com.triphuc22ad.shoesshop.presentation.admin.special_offer.list.AdminSpecialOfferUiState
import com.triphuc22ad.shoesshop.presentation.product.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

@Singleton
class AppStateRepository {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    fun resetAppState() {
        _appUiState.value = AppUiState()
    }

    fun updateAdminProductUiState(adminProductUiState: AdminProductUiState) {
        _appUiState.value = _appUiState.value.copy(
            adminProductUiState = adminProductUiState
        )
    }

    fun updateAdminBrandUiState(adminBrandUiState: AdminBrandUiState) {
        _appUiState.value = _appUiState.value.copy(
            adminBrandUIState = adminBrandUiState
        )
    }

    fun updateAdminOrderUiState(adminOrderUiState: AdminOrderUiState) {
        _appUiState.value = _appUiState.value.copy(
            adminOrderUiState = adminOrderUiState
        )
    }

    fun updateAdminInventoryUiState(adminInventoryUiState: AdminInventoryUiState) {
        _appUiState.value = _appUiState.value.copy(
            adminInventoryUiState = adminInventoryUiState
        )
    }

    fun updateAdminSpecialOfferUiState(adminSpecialOfferUiState: AdminSpecialOfferUiState) {
        _appUiState.value = _appUiState.value.copy(
            adminSpecialOfferUiState = adminSpecialOfferUiState
        )
    }

    fun updateProductUiState(productUiState: ProductUiState) {
        _appUiState.value = _appUiState.value.copy(
            productListUiState = productUiState
        )
    }

    fun updateUserInfo(userInfo: UserInfoResponse) {
        val role = if (userInfo.roles.contains("ROLE_ADMIN")) "ADMIN" else "USER"
        _appUiState.value = _appUiState.value.copy(
            isLoggedIn = true,
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

    fun showAdminBottomBar(isShowed: Boolean) {
        _appUiState.value = _appUiState.value.copy(showAdminBottomBar = isShowed)
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

    fun deleteCart() {
        _appUiState.value = _appUiState.value.copy(cartItems = emptyList())
    }

    fun checkInCart(productId: Int): Boolean {
        return _appUiState.value.cartItems.any { it.productId == productId }
    }
}