package com.triphuc22ad.shoesshop.presentation.admin.order.list

import com.triphuc22ad.shoesshop.domain.model.OrderInfo

data class AdminOrderUiState(
    val orderList: List<OrderInfo> = List(4) {
        OrderInfo(
            id = 0,
            status = "COMPLETED",
            description = "giao vao buoi chieu",
            price = 23000.0,
            shippingAddress = "tuyen quang, da nang"
        )
    },
    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,

    val searchId: Int = 0,
    val searchInfo: String = "",
)