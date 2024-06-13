package com.triphuc22ad.shoesshop.presentation.admin.product.add

import com.triphuc22ad.shoesshop.data.model.ProductRequest

data class AddProductUiState(
    val productToAdd: ProductRequest = ProductRequest(),
    val productToAddMainImgPreview: Long = 0,
)