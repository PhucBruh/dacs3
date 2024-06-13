package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

sealed class AdminEditInventoryEvent {
    data class ChangeStock(val stock: Int) : AdminEditInventoryEvent()
    data object Update : AdminEditInventoryEvent()
}