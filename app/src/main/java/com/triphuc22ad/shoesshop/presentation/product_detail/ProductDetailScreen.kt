package com.triphuc22ad.shoesshop.presentation.product_detail

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.components.PagerIndicator
import com.triphuc22ad.shoesshop.presentation.components.PriceBar
import com.triphuc22ad.shoesshop.presentation.components.QuantityButton
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ColorCircleButton
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ExpandedText
import com.triphuc22ad.shoesshop.presentation.product_detail.components.TextCircleButton
import com.triphuc22ad.shoesshop.presentation.util.parseColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import kotlin.math.absoluteValue

val listImgs = listOf(
    R.drawable.kd_15,
    R.drawable.curry_6,
    R.drawable.kd_15,
    R.drawable.curry_6,
    R.drawable.kd_15,
    R.drawable.curry_6,
    R.drawable.kd_15,
)

@Composable
fun ProductDetailScreen(
    onBackClick: () -> Unit = {},
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
) {

    val state by productDetailViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        productDetailViewModel.fetchData()
    }

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
                    images = state.product.imgs.map { it.url },
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(32.dp)) {
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
                            text = state.product.name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${state.product.totalSold} sold",
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
                        Text(text = "${state.product.rating}")
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
                            text = state.product.description,
                            expandedText = state.product.description,
                            expandedTextButton = " view more..",
                            shrinkTextButton = " less",
                        )
                    }

                    DetailContainerVertical(name = "Size") {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(items = state.product.sizes) { size ->
                                TextCircleButton(
                                    text = "${size.size}",
                                    onClick = {
                                        productDetailViewModel.onEvent(
                                            ProductDetailEvent.ChangeSize(
                                                size = size
                                            )
                                        )
                                    },
                                    active = size.id == state.selectedSize.id,
                                    size = 40.dp
                                )
                            }
                        }
                    }

                    DetailContainerVertical(name = "Color") {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.product.colors) { color ->
                                ColorCircleButton(
                                    color = parseColor(color.value),
                                    onClick = {
                                        productDetailViewModel.onEvent(
                                            ProductDetailEvent.ChangeColor(color)
                                        )
                                    },
                                    active = color.id == state.selectedColor.id,
                                    size = 40.dp
                                )
                            }
                        }
                    }

                    DetailContainerHorizontal(name = "Quantity") {
                        QuantityButton(
                            size = 44.dp,
                            quantity = state.quantity,
                            onIncrease = { productDetailViewModel.onEvent(ProductDetailEvent.IncreaseQuantity) },
                            onDecrease = { productDetailViewModel.onEvent(ProductDetailEvent.DecreaseQuantity) }
                        )
                    }
                }
            }
        }

        PriceBar(
            price = "${if (state.product.promotionalPrice != 0.0) state.product.promotionalPrice.toInt() else state.product.price.toInt()} vnđ",
            border = false,
            onAction = { productDetailViewModel.onEvent(ProductDetailEvent.AddToCart) },
            isDisableClick = state.isInCart,
        ) {
            Text(text = if (state.isInCart) "In Cart" else "Add to cart", fontSize = 14.sp)
        }
    }
}

@Composable
private fun ProductImagePager(
    images: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = {
        images.size
    })

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .height(320.dp)
            .fillMaxWidth()
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
                AsyncImage(
                    model = images[it],
                    contentDescription = null,
                    modifier = Modifier
                        .background(BgColor)
                        .size(280.dp)
                )
            }
        }


        Row(Modifier.padding(vertical = 16.dp)) {
            if (images.size > 1) {
                PagerIndicator(
                    pagerState = pagerState,
                    indicatorCount = if (images.size > 4) 4 else images.size,
                    animationDurationInMillis = 500
                )
            }
        }
    }
}


@Composable
private fun DetailContainerVertical(
    name: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
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
    content: @Composable () -> Unit,
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
    AppTheme {
        Surface {
            ProductDetailScreen()
        }
    }
}