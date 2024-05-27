package com.triphuc22ad.shoesshop.presentation.admin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Model cho đơn hàng
data class Order(val id: Int, val customerName: String, val totalAmount: Double, val status: String)

// Danh sách đơn hàng giả định
val orders = listOf(
    Order(1, "Customer 1", 50.0, "Pending"),
    Order(2, "Customer 2", 100.0, "Pending"),
    Order(3, "Customer 3", 75.0, "Approved")
)

@Composable
fun OrderScreen(orders: List<Order>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(orders) { order ->
            AdminOrderCard(order = order)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AdminOrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "name: ${order.customerName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Total: $${order.totalAmount}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Status: ${order.status}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.clickable { /* Handle edit icon click */ }
                )

                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.clickable { /* Handle delete icon click */ }
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewAdminOrderList() {
    OrderScreen(orders = orders)
}
