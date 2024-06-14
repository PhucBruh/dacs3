package com.triphuc22ad.shoesshop.presentation.product

import com.triphuc22ad.shoesshop.domain.model.Product

data class ProductUiState(
    val productList: List<Product> = emptyList(),
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val filter: ProductListFilter = ProductListFilter(),
    val query: String = "",
    val applyFilter: Boolean = false,
)

data class ProductListFilter(
    val minPrice: Double = 0.0,
    val maxPrice: Double = 1000000000.0,
    val saleStatus: String = "NORMAL",
    val orderBy: String = "NAME",
    val sortBy: String = "ASCENDING",
)