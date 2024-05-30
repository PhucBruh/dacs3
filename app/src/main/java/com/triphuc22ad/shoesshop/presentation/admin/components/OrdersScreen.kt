package com.triphuc22ad.shoesshop.presentation.admin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.BgColor


data class Order(val customerName: String,
                 val totalAmount: Double,
                 val status: String,
                 val imageResourceId: Int
    )


val orders = listOf(
    Order( "Customer 1", 50.0, "Pending", R.drawable.curry_6),
    Order( "Customer 2", 100.0, "Pending", R.drawable.curry_6),
    Order( "Customer 3", 75.0, "Approved", R.drawable.curry_6)
)

@Composable
fun OrderScreen(orders: List<Order>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(orders) { order ->
            AdminOrderCard(order = order, onEditClick = {
                // Xử lý sự kiện chỉnh sửa ở đây
            })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AdminOrderCard(order: Order, onEditClick: () -> Unit) {
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
                Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 5.dp)
                    .shadow(2.dp, RoundedCornerShape(32.dp))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .background(BgColor, RoundedCornerShape(32.dp))
                ) {
                    Image(
                        painter = painterResource(id = order.imageResourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = order.customerName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${order.totalAmount}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.status,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.clickable { onEditClick() }
                )

                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.clickable {  }
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
