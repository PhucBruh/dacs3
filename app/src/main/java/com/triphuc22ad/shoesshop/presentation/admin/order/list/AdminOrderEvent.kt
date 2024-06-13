package com.triphuc22ad.shoesshop.presentation.admin.order.list

sealed class AdminOrderEvent {
    data class ChangeQuery(val value: String) : AdminOrderEvent()
    data class ChangeQueryProductId(val value: Int) : AdminOrderEvent()
    data object Search : AdminOrderEvent()
}