package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.rangeTo
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.ui.theme.BgColor

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(BgColor)
                .clickable { onClick() }) {
            AsyncImage(
                model = product.img_url,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .background(BgColor)
            )
            Box(
                contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize()
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
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Text(
            text = product.name,
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
            Text(text = "${product.rating}")
            Spacer(modifier = Modifier.width(8.dp))
            VerticalDivider(modifier = Modifier.height(14.dp), color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${product.totalSold}",
                fontSize = 12.sp,
                modifier = Modifier
                    .background(BgColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 2.dp, horizontal = 6.dp)
            )
        }
        Text(
            text = "${product.price}", fontWeight = FontWeight.Bold, fontSize = 22.sp
        )
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    AppTheme {
        Surface {
            ProductCard(
                product = Product(
                    name = "Product 1",
                    description = "",
                    rating = 4.5f,
                    price = 100000.0,
                    totalSold = 100,
                    brand = "Nike",
                    img_url = "",
                    isFavorite = false,
                    colors = listOf(
                        Pair("Red", Color.Red.value.toFloat()),
                    ),
                    sizes = listOf(41, 42, 43)
                )
            )
        }
    }
}