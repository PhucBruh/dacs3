package com.triphuc22ad.shoesshop.domain.model

data class Cart(
    // Product - quantity
    val items: List<Pair<Product, Int>>,
    val totalPrice: Double
)
