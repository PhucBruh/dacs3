package com.triphuc22ad.shoesshop.presentation.order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.domain.model.OrderInfo

@Composable
fun CompletedScreen(
    completedOrders: List<OrderInfo>,
) {

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(completedOrders) {
                Row(Modifier.padding(vertical = 8.dp)) {
                    OrderItem(onClick = { /*TODO*/ }, order = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CompletedScreen(List(4) {
        OrderInfo(
            id = it,
            description = "giao vao buoi chieu",
            shippingAddress = "da nang",
            price = 100000.0,
            status = "COMPLETED"
        )
    })
}