package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Inventory
import com.triphuc22ad.shoesshop.domain.model.Size

data class AdminEditInventoryUiState(
    val inventoryToEdit: Inventory =
        Inventory(
            id = 0,
            size = Size(id = 0, size = 43),
            color = Color(id = 0, name = "red", value = "#ff0000"),
            productId = 0,
            productName = "kd 15",
            stock = 10
        ),
)