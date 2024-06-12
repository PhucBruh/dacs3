package com.triphuc22ad.shoesshop.presentation.product_detail

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Size

sealed class ProductDetailEvent {
    data class ChangeColor(val color: Color) : ProductDetailEvent()
    data class ChangeSize(val size: Size) : ProductDetailEvent()
    data object IncreaseQuantity : ProductDetailEvent()
    data object DecreaseQuantity : ProductDetailEvent()
    data object AddToCart : ProductDetailEvent()
}