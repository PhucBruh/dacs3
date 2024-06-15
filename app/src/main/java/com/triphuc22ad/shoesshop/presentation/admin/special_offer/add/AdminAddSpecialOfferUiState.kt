package com.triphuc22ad.shoesshop.presentation.admin.special_offer.add

data class AdminAddSpecialOfferUiState(
    val productId: Int = 0,
    val name: String = "",
    val description: String = "",
    val value: Double = 0.0
)