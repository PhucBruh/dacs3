package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme


@Composable
fun BrandItem(
    brand: Brand,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .background(BgColor, CircleShape)
                .clip(CircleShape)
                .clickable { onClick() }
        ) {
            AsyncImage(
                model = brand.img_url, // Replace with your actual image URL
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = brand.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun BrandItemPreview() {
    AppTheme {
        Surface {
            BrandItem(
                brand = Brand(
                    id = null,
                    name = "",
                    img_url = "https://image.similarpng.com/very-thumbnail/2021/12/Nike-logo-icon-on-transparent-background-PNG.png"
                ),
                onClick = {}
            )
        }
    }
}
