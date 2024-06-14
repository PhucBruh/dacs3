package com.triphuc22ad.shoesshop.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.triphuc22ad.shoesshop.R

data class Order(
    val id: Int,
    val user: User,
    val shippingAddress: String,
    val description: String,
    val details: List<OrderDetail>,
    val price: Double,
    val status: String
)

data class User(
    val name: String,
    val phone: String
)

data class OrderDetail(
    val productName: String,
    val productImg: Int,
    val size: Int,
    val color: String,
    val quantity: Int,
    val price: Double
)

@Composable
fun OrderInfor(order: Order) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Order ID: ${order.id}", fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                Text("Customer: ${order.user.name}", fontSize = 15.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                Text("Phone: ${order.user.phone}", fontSize = 15.sp)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "")
            Text("Shipping Address: ${order.shippingAddress}", fontSize = 15.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Description, contentDescription = "")
            Text("Description: ${order.description}", fontSize = 15.sp)
        }


        order.details.forEach { detail ->
            OrderDetailItem(detail)
    }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ShoppingBasket, contentDescription = "")
            Text("Total Price: ${order.price}", fontSize = 15.sp)
        }


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Recommend, contentDescription = "")
            Text("Status: ${order.status}", fontSize = 15.sp)
        }
    }
}

@Composable
fun OrderDetailItem(detail: OrderDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

            Image(
                painter = painterResource(id = detail.productImg),
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
                Text(text = "Color: ${detail.color}")
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
                Text(text = "Price: ${detail.price}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun orderInforPreview() {

    val order = Order(
        id = 1,
        user = User(name = "phuc nguyen", phone = "0797826527"),
        shippingAddress = "da nang",
        description = "giao vao buoi chieu",
        details = listOf(
            OrderDetail(
                productName = "kd 5",
                productImg = R.drawable.curry_6,
                size = 41,
                color = "red",
                quantity = 3,
                price = 360000.0
            )
        ),
        price = 360000.0,
        status = "COMPLETED"
    )
    OrderInfor(order = order)

}
