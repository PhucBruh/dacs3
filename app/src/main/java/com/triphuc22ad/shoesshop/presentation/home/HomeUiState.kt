package com.triphuc22ad.shoesshop.presentation.home

import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer

data class HomeUiState(
    val listBrand: List<Brand> = emptyList(),
    val listPopularProduct: List<Product> = emptyList(),
    val specialOffer: SpecialOffer? = null,
)