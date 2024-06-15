package com.triphuc22ad.shoesshop.presentation.admin.product.list

sealed class AdminProductEvent {
    data class ChangeQuery(val value: String) : AdminProductEvent()
    data class ChangeQueryProductId(val value: Int) : AdminProductEvent()
    data object Search : AdminProductEvent()
    data class Delete(val id: Int) : AdminProductEvent()
    data object NextPage : AdminProductEvent()
    data object PreviousPage : AdminProductEvent()
}