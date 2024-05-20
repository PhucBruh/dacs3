package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun PriceBar(
    price: String,
    border: Boolean = true,
    modifier: Modifier = Modifier,
    actionButtonContent: @Composable () -> Unit,
) {

    Column(
        verticalArrangement = if (border) Arrangement.Center  else Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .let {
                if (border)
                    it
                        .background(
                            Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .border(
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .height(100.dp)
                else it
                    .background(Color.White)
                    .height(80.dp)
            }
    ) {
        if (!border) HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Text(text = "Total Price", fontSize = 12.sp, fontWeight = FontWeight.Light)
                Text(text = price, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(height = 52.dp, width = 200.dp)
                ) {
                    actionButtonContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPricePreview() {
    AppTheme {
        Surface {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BgColor
                    )
            ) {
                PriceBar(price = "$555.555") {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Checkout", fontSize = 17.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}
