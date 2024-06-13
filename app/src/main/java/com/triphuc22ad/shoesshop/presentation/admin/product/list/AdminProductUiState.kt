package com.triphuc22ad.shoesshop.presentation.admin.product.list

import com.triphuc22ad.shoesshop.domain.model.Product

data class AdminProductUiState(
    val productList: List<Product> = List(4) {
        Product(
            id = it,
            status = "ACTIVE",
            name = "kd 15",
            rating = 5.0,
            price = 100000.0,
            description = "",
            mainImg = "",
            totalSold = 1000,
            promotionPrice = 10000.0
        )
    },
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)