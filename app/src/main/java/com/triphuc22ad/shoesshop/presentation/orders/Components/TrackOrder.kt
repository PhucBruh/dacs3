package com.triphuc22ad.shoesshop.presentation.orders.Components


import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stroller
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun TrackOrderScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),

        ) {
        TopTitleBar(name = "Track Order", modifier = Modifier.padding(top = 16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CardReviewItem(status = "In Delivery")


            Track(
                items = 4,
                brush = { index ->
                    Brush.linearGradient(
                        colors = listOf(Color.Black),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                lineWidth = 2.dp,
                pathEffect = { index ->

                    PathEffect.dashPathEffect(floatArrayOf(30f, 35f), 10f)
                },
                iconstart  = listOf(
                    { Icon(imageVector = Icons.Default.GifBox, tint = Color.Black, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    { Icon(imageVector = Icons.Default.LocalShipping, tint = Color.Black, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    { Icon(imageVector = Icons.Default.Stroller, tint = Color.Black, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    { Icon(imageVector = Icons.Default.OpenInBrowser, tint = Color.Black, contentDescription = null, modifier = Modifier.size(24.dp)) },
            ),
                iconend = { index ->

                    Box(
                        modifier = Modifier.size(30.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color.Black, shape = CircleShape)
                        ){
                            Icon(
                                imageVector = Icons.Default.Done,
                                tint = Color.White,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            )

            Text(
                text = "Packet In Delivery",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        HorizontalDivider(Modifier.padding(top = 8.dp, bottom = 8.dp))

        Column {
            Text(
                text = "Order Status Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }

}

@Composable
fun Track(
    items: Int,
    brush: (from: Int) -> Brush,
    modifier: Modifier = Modifier,
    lineWidth: Dp = 1.dp,
    pathEffect: ((from: Int) -> androidx.compose.ui.graphics.PathEffect?)? = null,
    iconstart:  List<@Composable () -> Unit>,
    iconend: @Composable (index: Int) -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(-1f)
        ) {
            val width = drawContext.size.width
            val height = drawContext.size.height

            val yOffset = height / 2
            val itemWidth = width / items

            var startOffset = itemWidth / 2
            var endOffset = startOffset

            val barWidth = lineWidth.toPx()
            repeat(items - 1) {
                endOffset += itemWidth
                drawLine(
                    brush = brush.invoke(it),
                    start = Offset(startOffset, yOffset),
                    end = Offset(endOffset, yOffset),
                    strokeWidth = barWidth,
                    pathEffect = pathEffect?.invoke(it)
                )
                startOffset = endOffset
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(items) { index ->

                Column(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    iconstart[index]()
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        iconend.invoke(index)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TrackOrderScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            TrackOrderScreen()
        }
    }
}