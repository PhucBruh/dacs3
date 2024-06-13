package com.triphuc22ad.shoesshop.presentation.admin.brand.list

import com.triphuc22ad.shoesshop.domain.model.Brand

data class AdminBrandUiState(
    val brandList: List<Brand> = List(4) {
        Brand(
            id = it,
            name = "Brand $it",
            img_url = ""
        )
    },
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)