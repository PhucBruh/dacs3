package com.triphuc22ad.shoesshop.presentation.admin.order.edit

import com.triphuc22ad.shoesshop.domain.model.OrderDetail
import com.triphuc22ad.shoesshop.domain.model.OrderInfo

data class AdminEditOrderUiState(
    val orderToEdit: OrderDetail = OrderDetail(),
)