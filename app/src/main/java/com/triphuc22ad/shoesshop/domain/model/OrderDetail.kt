package com.triphuc22ad.shoesshop.domain.model

data class OrderDetail(
    val description: String = "",
    val details: List<Detail> = emptyList(),
    val id: Int = 0,
    val price: Double = 0.0,
    val shippingAddress: String = "",
    val status: String = "",
    val user: OrderUserInfo = OrderUserInfo(),
)

data class Detail(
    val color: String,
    val colorValue: String,
    val price: Double,
    val productId: Int,
    val productImg: String,
    val productName: String,
    val quantity: Int,
    val size: Int,
)

data class OrderUserInfo(
    val name: String = "",
    val phone: String = "",
)