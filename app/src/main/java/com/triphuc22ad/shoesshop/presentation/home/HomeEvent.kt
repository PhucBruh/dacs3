package com.triphuc22ad.shoesshop.presentation.home

import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer

sealed class HomeEvent {
    data class ClickBrand(val brand: Brand) : HomeEvent()
    data class ClickProduct(val product: Product) : HomeEvent()
    data class ClickSpecialOffer(val specialOffer: SpecialOffer) : HomeEvent()
    data object SeeAllSpecialOffer : HomeEvent()
    data object SeeAllBrand : HomeEvent()
    data class SeeAllProduct(val navigateToProduct: () -> Unit) : HomeEvent()
}