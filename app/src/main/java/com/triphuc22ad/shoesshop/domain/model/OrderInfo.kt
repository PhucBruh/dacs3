package com.triphuc22ad.shoesshop.domain.model

data class OrderInfo(
    val description: String,
    val id: Int,
    val price: Double,
    val shippingAddress: String,
    val status: String
)