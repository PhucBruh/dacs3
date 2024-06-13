package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Inventory
import com.triphuc22ad.shoesshop.domain.model.Size

data class AdminEditInventoryUiState(
    val inventoryToEdit: Inventory =
        Inventory(
            id = 0,
            size = Size(id = 0, size = 0),
            color = Color(id = 0, name = "", value = ""),
            productId = 0,
            productName = "",
            stock = 0
        ),
)