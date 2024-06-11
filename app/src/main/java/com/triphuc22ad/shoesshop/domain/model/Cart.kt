package com.triphuc22ad.shoesshop.domain.model

data class Cart(
    // Product - quantity
    val items: List<Pair<ProductInfo, Int>>,
    val totalPrice: Double
)
