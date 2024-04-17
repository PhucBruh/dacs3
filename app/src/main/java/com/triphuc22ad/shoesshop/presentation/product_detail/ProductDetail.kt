package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ColorCircleButton
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ExpandedText
import com.triphuc22ad.shoesshop.presentation.product_detail.components.TextCircleButton
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.PageIndicator
import com.triphuc22ad.shoesshop.util.component.PagerIndicator
import com.triphuc22ad.shoesshop.util.component.PriceBar
import com.triphuc22ad.shoesshop.util.component.QuantityButton
import kotlin.math.absoluteValue
import kotlin.random.Random

@Composable
fun ProductDetailScreen() {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                ProductImagePager(
                    images = listOf(
                        R.drawable.curry_6,
                        R.drawable.curry_6,
                        R.drawable.kd_15,
                        R.drawable.curry_6,
                        R.drawable.kd_15,
                        R.drawable.curry_6,
                        R.drawable.kd_15,
                        R.drawable.kd_15,
                    ), modifier = Modifier.fillMaxSize()
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "New Balance 996V2",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "8,374 sold",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(BgColor, RoundedCornerShape(8.dp))
                                .padding(vertical = 2.dp, horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Black
                        )
                        Text(text = "4.9 (6.573 reviews)")
                    }

                    HorizontalDivider()
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {

                    DetailContainerVertical(name = "Description") {
                        ExpandedText(
                            text = stringResource(id = R.string.nofull),
                            expandedText = stringResource(id = R.string.full),
                            expandedTextButton = " view more..",
                            shrinkTextButton = " less",
                        )
                    }

                    DetailContainerVertical(name = "Size") {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(4) {
                                TextCircleButton(text = "4$it", onClick = {}, size = 40.dp)
                            }
                        }
                    }

                    DetailContainerVertical(name = "Color") {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(4) {
                                ColorCircleButton(
                                    color = Color(
                                        red = Random.nextInt(256),
                                        blue = Random.nextInt(256),
                                        green = Random.nextInt(256),
                                    ), onClick = {}, size = 40.dp
                                )
                            }
                        }
                    }

                    DetailContainerHorizontal(name = "Quantity") {
                        QuantityButton(size = 44.dp)
                    }
                }
            }
        }

        PriceBar(price = "$5.555", border = false) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add to Cart", fontSize = 17.sp)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductImagePager(
    images: List<Int>,
    modifier: Modifier = Modifier
        .height(320.dp)
        .fillMaxWidth()
) {
    val pagerState = rememberPagerState(pageCount = {
        images.size
    })

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .background(BgColor)
    ) {
        HorizontalPager(state = pagerState) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - it) + pagerState
                                    .currentPageOffsetFraction
                                )

                        alpha = lerp(
                            start = 0.4f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        )

                        cameraDistance = 8 * density

                        rotationY = lerp(
                            start = 0f,
                            stop = 80f,
                            fraction = pageOffset.coerceIn(-1f, 1f),
                        )

                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
            ) {
                Image(
                    painter = painterResource(images[it]),
                    contentDescription = "giay",
                    modifier = Modifier
                        .background(BgColor)
                        .size(280.dp)
                )
            }
        }


        Row(Modifier.padding(vertical = 16.dp)) {
            PagerIndicator(
                pagerState = pagerState,
                indicatorCount = if (images.size > 6) 6 else images.size,
                selectedColor = Color.Black,
                defaultColor = Color.White,
                defaultRadius = 8.dp,
                selectedLength = 28.dp,
                space = 6.dp,
                animationDurationInMillis = 700,
            )
        }
    }
}


@Composable
private fun DetailContainerVertical(
    name: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )

        content()
    }
}

@Composable
private fun DetailContainerHorizontal(
    name: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )

        content()
    }
}

@Preview
@Composable
fun PreviewProductDetailScreen() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProductDetailScreen()
        }
    }
}