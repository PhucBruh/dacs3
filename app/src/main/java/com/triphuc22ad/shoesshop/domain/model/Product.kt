package com.triphuc22ad.shoesshop.domain.model

data class Product(
    val id: Int? = null,
    val name: String,
    val description: String,
    val price: Double,
    val colors: List<Pair<String, Float>>,
    val sizes: List<Int>,
    val brand: String,
    val isFavorite: Boolean,
    val totalSold: Int,
    val rating: Float,
    val img_url: String,
)