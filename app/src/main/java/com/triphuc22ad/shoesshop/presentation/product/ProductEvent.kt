package com.triphuc22ad.shoesshop.presentation.product

sealed class ProductEvent {
    data class ClickProduct(val productId: Int) : ProductEvent()
    data class ChangeQuery(val query: String) : ProductEvent()
    data object Search : ProductEvent()
    data object LoadMore : ProductEvent()
    data object ResetSearch : ProductEvent()
}