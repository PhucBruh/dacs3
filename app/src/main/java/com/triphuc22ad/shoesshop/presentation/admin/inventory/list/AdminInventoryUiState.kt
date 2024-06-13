package com.triphuc22ad.shoesshop.presentation.admin.inventory.list

import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Inventory
import com.triphuc22ad.shoesshop.domain.model.Size

data class AdminInventoryUiState(
    val inventoryList: List<Inventory> = List(4) {
        Inventory(
            id = 0,
            size = Size(id = 0, size = 43),
            color = Color(id = 0, name = "red", value = "#ff0000"),
            productId = 0,
            productName = "kd 15",
            stock = 10
        )
    },
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)