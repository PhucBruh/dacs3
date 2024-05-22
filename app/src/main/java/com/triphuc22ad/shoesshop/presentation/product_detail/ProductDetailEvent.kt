package com.triphuc22ad.shoesshop.presentation.product_detail

sealed class ProductDetailEvent {
    data class ChangeColor(val color: String) : ProductDetailEvent()
    data class ChangeSize(val size: Int) : ProductDetailEvent()
    data object IncreaseQuantity : ProductDetailEvent()
    data object DecreaseQuantity : ProductDetailEvent()
}