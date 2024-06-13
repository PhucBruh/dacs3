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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import com.triphuc22ad.shoesshop.presentation.util.limitText
import com.triphuc22ad.shoesshop.ui.theme.BgColor

val orders = listOf(
    OrderInfo(
        id = 10,
        price = 1000.0,
        status = "COMPLETED",
        description = "gia vao buoi chieu",
        shippingAddress = "da nang"
    )
)

@Composable
fun OrderScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(orders) {
            AdminOrderCard(order = it, onEditClick = {})
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AdminOrderCard(
    order: OrderInfo,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
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
                        .size(80.dp)
                        .background(BgColor, RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(
                            id = if (order.status == "COMPLETED"
                            ) R.drawable.completed_order else R.drawable.incompleted_order
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "ID: ${order.id}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = limitText("Address: ${order.shippingAddress}", maxLength = 16),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = limitText("Description: ${order.description}", maxLength = 16),
                    overflow = TextOverflow.Ellipsis,
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
                    modifier = Modifier.clickable { onDeleteClick() }
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewAdminOrderList() {
    OrderScreen()
}
