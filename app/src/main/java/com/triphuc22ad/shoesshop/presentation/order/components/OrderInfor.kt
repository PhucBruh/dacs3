package com.triphuc22ad.shoesshop.presentation.order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.domain.model.Detail
import com.triphuc22ad.shoesshop.domain.model.OrderDetail
import com.triphuc22ad.shoesshop.domain.model.OrderUserInfo
import com.triphuc22ad.shoesshop.presentation.util.formatPrice
import com.triphuc22ad.shoesshop.presentation.util.parseColor

@Composable
fun OrderInfo(order: OrderDetail) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text("Order ID: ${order.id}", fontWeight = FontWeight.Bold)
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                Text("Customer: ${order.user.name}", fontSize = 16.sp)
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                Text("Phone: ${order.user.phone}", fontSize = 16.sp)
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
                Text("Shipping Address: ${order.shippingAddress}", fontSize = 16.sp)
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Description, contentDescription = "")
                Text("Description: ${order.description}", fontSize = 16.sp)
            }
        }

        items(order.details) {
            OrderDetailItem(it)
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ShoppingBasket, contentDescription = "")
                Text("Total Price: ${formatPrice(order.price)}", fontSize = 16.sp)
            }
        }


        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Recommend, contentDescription = "")
                Text("Status: ${order.status}", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun OrderDetailItem(detail: Detail) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        AsyncImage(
            model = detail.productImg,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp),
        )

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "")
                Text(text = "Name: ${detail.productName}")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.FormatSize, contentDescription = "")
                Text(text = "Size: ${detail.size}")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ColorLens, contentDescription = "")
                Text(text = "Color: ${detail.color}   ")
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            parseColor(detail.colorValue),
                            CircleShape
                        )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ProductionQuantityLimits, contentDescription = "")
                Text(text = "Quantity: ${detail.quantity}")
            }


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.AttachMoney, contentDescription = "")
                Text(text = "Price: ${formatPrice(detail.price)}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderInfoPreview() {
    val order = OrderDetail(
        id = 1,
        user = OrderUserInfo(name = "phuc nguyen", phone = "0797826527"),
        shippingAddress = "da nang",
        description = "giao vao buoi chieu",
        details = List(4) {
            Detail(
                productName = "kd 5",
                productId = 0,
                productImg = "",
                size = 41,
                color = "red",
                colorValue = "#03fcad",
                quantity = 3,
                price = 360000.0
            )
        },
        price = 360000.0,
        status = "COMPLETED"
    )
    OrderInfo(order = order)

}
