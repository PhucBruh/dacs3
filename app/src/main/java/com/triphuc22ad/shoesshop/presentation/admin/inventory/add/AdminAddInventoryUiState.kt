package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import com.triphuc22ad.shoesshop.domain.model.Color
import com.triphuc22ad.shoesshop.domain.model.Size

data class AdminAddInventoryUiState(
    val productId: Int = 0,
    val selectedColorId: Int = 0,
    val selectedSizeId: Int = 0,
    val colorCheckList: List<Color> = emptyList(),
    val sizeCheckList: List<Size> = emptyList(),
)