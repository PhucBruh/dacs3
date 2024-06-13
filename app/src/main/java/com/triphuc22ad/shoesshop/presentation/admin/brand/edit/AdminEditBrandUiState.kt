package com.triphuc22ad.shoesshop.presentation.admin.brand.edit

import com.triphuc22ad.shoesshop.domain.model.Brand

data class AdminEditBrandUiState(
    val brandToEdit: Brand = Brand(
        id = 0,
        name = "",
        img_url = ""
    ),
    val productDetailMainImgPreview: Long = 0L,
)