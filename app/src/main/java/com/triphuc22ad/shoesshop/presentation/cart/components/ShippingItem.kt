package com.triphuc22ad.shoesshop.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme


@Composable
fun ShippingItem(
    icon: ImageVector,
    description: String,
    title: String,
    price: String,
    modifier: Modifier = Modifier
) {

    val (selected, setSelected) = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(BgColor, RoundedCornerShape(32.dp))
            .height(95.dp)
    ) {
            Column(
                Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 20.dp)
                    .padding(end = 12.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, RoundedCornerShape(50.dp))
                ) {

                        Icon(
                            imageVector = icon, contentDescription = "",
                            tint = Color.Black,
                        )


                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .height(120.dp)
                    .padding(end = 20.dp)
                    .padding(vertical = 25.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Text(
                    text = description,
                    maxLines = 2,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }

        Text(text = price,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)

        RadioButton(selected = selected, onClick = { setSelected(!selected)})

    }
}


@Preview
@Composable
fun ShippingItemPreview() {
    AppTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            ShippingItem(icon = Icons.Default.LocalShipping, title = "home", description = "fasgfas vrer tsvdag", price = "188$")
        }
    }
}