package com.triphuc22ad.shoesshop.presentation.admin.product.list

import com.triphuc22ad.shoesshop.domain.model.Product

data class AdminProductUiState(
    val productList: List<Product> = emptyList(),
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)