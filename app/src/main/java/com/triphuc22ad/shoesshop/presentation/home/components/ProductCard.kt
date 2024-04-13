package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun ProductCard() {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(BgColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.curry_6),
                contentScale = ContentScale.Fit,
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .background(BgColor)
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                        .background(Color.Black, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
        Text(
            text = "Shoes n...",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Rating")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "4.5")
            Spacer(modifier = Modifier.width(8.dp))
            VerticalDivider(modifier = Modifier.height(20.dp), color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "8,374 sold",
                modifier = Modifier
                    .background(BgColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 2.dp, horizontal = 6.dp)
            )
        }
        Text(
            text = "$85.00",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProductCard()
        }
    }
}