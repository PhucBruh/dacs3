package com.triphuc22ad.shoesshop.data.model

data class ProductRequest(
    val colors: List<ColorRequest> = emptyList(),
    val brandId: Int = 0,
    val description: String = "",
    val imgs: List<String> = emptyList(),
    val mainImg: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val sizes: List<Int> = emptyList(),
    val status: String = "ACTIVE",
)

data class ColorRequest(
    val name: String,
    val value: String,
)