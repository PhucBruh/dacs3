package com.triphuc22ad.shoesshop.domain.model

data class Inventory(
    val id: Int,
    val productId: Int,
    val productName: String,
    val size: Size,
    val color: Color,
    val stock: Int,
)
