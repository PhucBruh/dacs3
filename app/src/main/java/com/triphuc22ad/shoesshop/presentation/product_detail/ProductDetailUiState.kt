package com.triphuc22ad.shoesshop.presentation.product_detail

import com.triphuc22ad.shoesshop.domain.model.Product

data class ProductDetailUiState(
    val product: Product,
    val selectedSize: Int? = null,
    val selectedColor: String? = null,
    val isInCart: Boolean = false,
    val quantity: Int = 1,
)