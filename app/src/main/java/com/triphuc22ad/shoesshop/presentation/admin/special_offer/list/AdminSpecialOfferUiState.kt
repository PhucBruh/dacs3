package com.triphuc22ad.shoesshop.presentation.admin.special_offer.list

import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer

data class AdminSpecialOfferUiState(
    val specialOfferList: List<SpecialOffer> = List(4) {
        SpecialOffer(
            value = 25.0,
            name = "Special Offer $it",
            description = "Description $it",
            img = "https://www.jordan1.vn/wp-content/uploads/2023/09/3022893_409.png_77c2f15034cf4fa3b92f07195024f407.png",
            active = true,
            productId = 1
        )
    },
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)