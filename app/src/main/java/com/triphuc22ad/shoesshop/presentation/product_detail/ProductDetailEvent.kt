package com.triphuc22ad.shoesshop.presentation.product_detail

sealed class ProductDetailEvent {
    data class ChangeColor(val colorId: Int) : ProductDetailEvent()
    data class ChangeSize(val sizeId: Int) : ProductDetailEvent()
    data object IncreaseQuantity : ProductDetailEvent()
    data object DecreaseQuantity : ProductDetailEvent()
    data object AddToCart : ProductDetailEvent()
}