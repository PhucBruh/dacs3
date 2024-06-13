package com.triphuc22ad.shoesshop.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.Detail
import com.triphuc22ad.shoesshop.domain.model.Size
import com.triphuc22ad.shoesshop.presentation.app.CartItem
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun CardOrder(
    cartItem: CartItem,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(32.dp))
    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(start = 20.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp)
                    .background(BgColor, RoundedCornerShape(32.dp))
            ) {
                AsyncImage(
                    model = cartItem.productImg,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(120.dp)
                .padding(end = 20.dp)
                .padding(vertical = 6.dp)
        ) {

            Text(
                text = cartItem.productName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.Black, CircleShape)
                )
                Text(text = cartItem.color.name, fontWeight = FontWeight.Light, fontSize = 14.sp)
                VerticalDivider(
                    color = Color.Black,
                    modifier = Modifier.height(12.dp)
                )
                Text(
                    text = "Size = ${cartItem.size.size}",
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "${cartItem.price.toInt()} vnđ")

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Gray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${cartItem.quantity}")
                }
            }
        }
    }
}

@Composable
fun CardOrder(
    detail: Detail,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(32.dp))
    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(start = 20.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp)
                    .background(BgColor, RoundedCornerShape(32.dp))
            ) {
                AsyncImage(
                    model = detail.productImg,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(120.dp)
                .padding(end = 20.dp)
                .padding(vertical = 6.dp)
        ) {

            Text(
                text = detail.productName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.Black, CircleShape)
                )
                Text(text = detail.color, fontWeight = FontWeight.Light, fontSize = 14.sp)
                VerticalDivider(
                    color = Color.Black,
                    modifier = Modifier.height(12.dp)
                )
                Text(
                    text = "Size = ${detail.size}",
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "${detail.price.toInt()} vnđ")

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Gray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${detail.quantity}")
                }
            }
        }
    }
}


@Preview
@Composable
fun CardOrderPreview() {
    AppTheme {
        CardOrder(
            cartItem = CartItem(
                productId = 10,
                price = 150000.0,
                promotionPrice = 0.0,
                size = Size(10, 42),
                color = com.triphuc22ad.shoesshop.domain.model.Color(
                    10,
                    name = "red",
                    value = "#ffffff"
                ),
                productImg = "imgTest",
                quantity = 10,
                productName = "Curry 6"
            )
        )
    }
}