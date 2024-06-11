package com.triphuc22ad.shoesshop.presentation.order

import com.triphuc22ad.shoesshop.domain.model.OrderInfo

data class OrderUiState(
    val inCompletedOrders: List<OrderInfo> = emptyList(),
    val completedOrders: List<OrderInfo> = emptyList(),
)