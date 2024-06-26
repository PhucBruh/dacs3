package com.triphuc22ad.shoesshop.presentation.order.components

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
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme


@Composable
fun CardReviewItem(
    modifier: Modifier = Modifier,
    status : String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(32.dp))

    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(start = 15.dp)

        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp)
                    .background(BgColor, RoundedCornerShape(32.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.curry_6),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(150.dp)
                .padding(end = 20.dp)
                .padding(vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Air Jodan 3 Retro",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }
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
                Text(text = "Black", fontWeight = FontWeight.Light, fontSize = 14.sp)
                VerticalDivider(
                    color = Color.Black,
                    modifier = Modifier.height(12.dp)
                )
                Text(text = "Size = 42", fontWeight = FontWeight.Light, fontSize = 14.sp)
                VerticalDivider(
                    color = Color.Black,
                    modifier = Modifier.height(12.dp)
                )
                Text(text = "Qty = 1", fontWeight = FontWeight.Light, fontSize = 14.sp)
            }

            Row {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(BgColor, RoundedCornerShape(10.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text(text = status)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "$105.00")
            }
        }
    }
}


@Preview
@Composable
fun CardReviewItemPreview() {
    AppTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .background(BgColor)
                .padding(16.dp)
        ) {
            CardReviewItem(status = "complete",)
        }
    }
}