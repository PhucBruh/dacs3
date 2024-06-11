package com.triphuc22ad.shoesshop.domain.model

data class Product(
    val id: Int,
    val description: String,
    val mainImg: String,
    val name: String,
    val price: Double,
    val promotionPrice: Double,
    val rating: Double,
    val status: String,
    val totalSold: Int,
)