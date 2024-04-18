package com.triphuc22ad.shoesshop.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme


@Composable
fun CartAddress(
    update: Boolean = true,
    onUpdate: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(BgColor, RoundedCornerShape(32.dp))
            .height(95.dp)
    ) {
        Row {
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
                        .background(Color.DarkGray, RoundedCornerShape(50.dp))
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, RoundedCornerShape(50.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Rating",
                            tint = Color.Black,
                        )
                    }

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
                    text = "Home",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Text(
                    text = "61480 Sunbrook Park ...",
                    maxLines = 2,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
        }


        if (update) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = { onUpdate() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.BorderColor,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CartItemPreview() {
    Dacs3shoesshopandroidTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            CartAddress(update = true)
        }
    }
}