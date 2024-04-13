package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme


@Composable
fun BrandItem(
    name: String,
    @DrawableRes logo: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
    ) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape) // Clip to a circle shape
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Composable
fun BrandItem(
    name: String,
    logoUrl: String,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoUrl)
                .build(),
            contentDescription = "nike logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun BrandItemPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            Row {
                BrandItem(
                    name = "Nike",
                    logo = R.drawable.nike_logo,
                    onClick = {}
                )
            }
        }
    }
}
