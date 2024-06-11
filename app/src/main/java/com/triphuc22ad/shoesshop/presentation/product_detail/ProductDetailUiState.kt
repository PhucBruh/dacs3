package com.triphuc22ad.shoesshop.presentation.product_detail

import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.ProductDetail

data class ProductDetailUiState(
    val product: ProductDetail = ProductDetail(
        id = 0,
        name = "",
        description = "",
        imgs = emptyList(),
        brand = Brand(id = 0, name = "", img_url = ""),
        sizes = emptyList(),
        colors = emptyList(),
        status = "",
        price = 0.0,
        promotionalPrice = 0.0,
        rating = 5.0,
        totalSold = 0,
        mainImg = ""
    ),
    val selectedSizeId: Int = 0,
    val selectedColorId: Int = 0,
    val isInCart: Boolean = false,
    val quantity: Int = 1,
)