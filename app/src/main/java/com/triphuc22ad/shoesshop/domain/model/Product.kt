package com.triphuc22ad.shoesshop.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val colors: List<Pair<String, Float>>,
    val sizes: List<Pair<String, Int>>,
    val brand: String,
    val isFavorite: Boolean,
    val totalSold: Int,
    val rating: Float
)