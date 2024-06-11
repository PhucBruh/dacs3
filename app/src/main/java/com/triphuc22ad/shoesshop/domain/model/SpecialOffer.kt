package com.triphuc22ad.shoesshop.domain.model

data class SpecialOffer(
    val id: Int? = null,
    val value: Double,
    val name: String,
    val description: String,
    val img: String,
    val active: Boolean,
    val productId: Int,
)