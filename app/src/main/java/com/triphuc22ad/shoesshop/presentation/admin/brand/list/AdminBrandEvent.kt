package com.triphuc22ad.shoesshop.presentation.admin.brand.list

sealed class AdminBrandEvent {
    data class ChangeQuery(val value: String) : AdminBrandEvent()
    data class ChangeQueryProductId(val value: Int) : AdminBrandEvent()
    data object Search : AdminBrandEvent()
    data class Delete(val id: Int) : AdminBrandEvent()
}