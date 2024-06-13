package com.triphuc22ad.shoesshop.presentation.admin.brand.list

sealed class AdminBrandEvent {
    data class Delete(val id: Int) : AdminBrandEvent()
}