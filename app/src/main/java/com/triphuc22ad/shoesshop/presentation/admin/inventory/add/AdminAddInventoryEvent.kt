package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Size

sealed class AdminAddInventoryEvent {
    data class ChangeProductId(val id: Int) : AdminAddInventoryEvent()
    data class ChangeSize(val size: Size) : AdminAddInventoryEvent()
    data class ChangeColor(val color: Color) : AdminAddInventoryEvent()
    data object CheckProduct : AdminAddInventoryEvent()
    data object Add : AdminAddInventoryEvent()
}