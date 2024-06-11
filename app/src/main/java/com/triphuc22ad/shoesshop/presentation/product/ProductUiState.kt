package com.triphuc22ad.shoesshop.presentation.product

import com.triphuc22ad.shoesshop.domain.model.Product

data class ProductUiState(
    val productList: List<Product> = emptyList(),
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,
)