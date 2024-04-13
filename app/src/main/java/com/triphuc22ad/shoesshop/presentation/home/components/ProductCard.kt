package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun ProductCard(
    @DrawableRes image: Int,
) {
    Column(
        modifier = Modifier.clickable { }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(BgColor)
        ) {
            Image(
                painter = painterResource(id = image),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
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
            VerticalDivider(modifier = Modifier.height(14.dp), color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "8,374 sold",
                fontSize = 12.sp,
                modifier = Modifier
                    .background(BgColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 2.dp, horizontal = 6.dp)
            )
        }
        Text(
            text = "$85.00",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}

@Composable
fun ProductCardList(
    modifier: Modifier = Modifier
) {
    val imgList = List(30) { R.drawable.curry_6 }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(imgList) { img ->
            ProductCard(image = img)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProductCardList()
        }
    }
}