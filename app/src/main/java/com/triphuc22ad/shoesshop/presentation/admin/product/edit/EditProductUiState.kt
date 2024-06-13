package com.triphuc22ad.shoesshop.presentation.admin.product.edit

import com.triphuc22ad.shoesshop.data.model.ColorRequest
import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.ProductDetail

data class EditProductUiState(
    val productDetail: ProductDetail = ProductDetail(
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
    val editColors: List<ColorRequest> = emptyList(),
    val editSizes: List<Int> = emptyList(),
    val editImgs: List<String> = emptyList(),
    val productDetailMainImgPreview: Long = 0L,
)