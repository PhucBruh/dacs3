package com.triphuc22ad.shoesshop.domain.model

data class ProductInfo(
    val id: Int? = null,
    val name: String,
    val description: String,
    val price: Double,
    val promotionPrice: Double,
    val colors: List<Pair<String, String>>,
    val sizes: List<Int>,
    val brand: String,
    val isFavorite: Boolean,
    val totalSold: Int,
    val rating: Double,
    val img_url: String,
    val imgs: List<String> = emptyList(),
)

data class ProductDetail(
    val brand: Brand,
    val colors: List<Color>,
    val description: String,
    val id: Int,
    val imgs: List<Img>,
    val mainImg: String,
    val name: String,
    val price: Double,
    val promotionalPrice: Double,
    val rating: Double,
    val sizes: List<Size>,
    val status: String,
    val totalSold: Int,
)

data class Color(
    val id: Int,
    val name: String,
    val value: String,
)

data class Img(
    val id: Int,
    val url: String,
)

data class Size(
    val id: Int,
    val size: Int,
)