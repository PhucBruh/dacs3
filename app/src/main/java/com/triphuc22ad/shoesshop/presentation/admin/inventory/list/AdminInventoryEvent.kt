package com.triphuc22ad.shoesshop.presentation.admin.inventory.list

sealed class AdminInventoryEvent {
    data class ChangeQuery(val value: String) : AdminInventoryEvent()
    data class ChangeQueryProductId(val value: Int) : AdminInventoryEvent()
    data class Delete(val value: Int) : AdminInventoryEvent()
    data object Search : AdminInventoryEvent()
}