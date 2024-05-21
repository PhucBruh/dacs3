package com.triphuc22ad.shoesshop.domain.model

data class SpecialOffer(
    val id: Int? = null,
    val value: Int,
    val name: String,
    val description: String,
    val img_url: String,
)
