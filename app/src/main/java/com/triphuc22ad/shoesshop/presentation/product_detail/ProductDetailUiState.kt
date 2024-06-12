package com.triphuc22ad.shoesshop.presentation.product_detail

import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.ProductDetail
import com.triphuc22ad.shoesshop.domain.model.Size

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
    val selectedSize: Size = Size(id = 0, size = 0),
    val selectedColor: Color = Color(id = 0, name = "", value = ""),
    val isInCart: Boolean = false,
    val quantity: Int = 1,
)