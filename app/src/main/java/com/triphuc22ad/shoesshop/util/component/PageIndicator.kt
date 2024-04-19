package com.triphuc22ad.shoesshop.util.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorCount: Int = 4,
    selectedColor: Color = Color.White,
    defaultColor: Color = Color.LightGray.copy(0.7f),
    containerColor: Color = Color.Black.copy(0.7f),
    defaultRadius: Dp = 8.dp,
    selectedLength: Dp = 20.dp,
    space: Dp = 6.dp,
    animationDurationInMillis: Int = 300,
) {
    val listState = rememberLazyListState()

    val totalWidth: Dp = defaultRadius * indicatorCount + space * (indicatorCount - 1)
    val widthInPx = LocalDensity.current.run { defaultRadius.toPx() }

    val currentItem by remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }

    val itemCount = pagerState.pageCount

    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        listState.animateScrollToItem(
            currentItem,
            (widthInPx / 2 - viewportSize.width / 2).toInt()
        )
    }


    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(vertical = space / 2),
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = false,
        modifier = modifier
            .width(totalWidth + defaultRadius * 4)
            .background(containerColor, RoundedCornerShape(defaultRadius * (1.5f)))
    ) {
        item { Spacer(modifier = Modifier.width(space)) }

        items(itemCount) { index ->

            val isSelected = (index == currentItem)

            // Index of item in center when odd number of indicators are set
            // for 5 indicators this is 2nd indicator place
            val centerItemIndex = indicatorCount / 2

            val right1 =
                (currentItem < centerItemIndex &&
                        index >= indicatorCount - 1)

            val right2 =
                (currentItem >= centerItemIndex &&
                        index >= currentItem + centerItemIndex &&
                        index <= itemCount - centerItemIndex + 1)
            val isRightEdgeItem = right1 || right2

            // Check if this item's distance to center item is smaller than half size of
            // the indicator count when current indicator at the center or
            // when we reach the end of list. End of the list only one item is on edge
            // with 10 items and 7 indicators
            // 7-3= 4th item can be the first valid left edge item and
            val isLeftEdgeItem =
                index <= currentItem - centerItemIndex &&
                        currentItem > centerItemIndex &&
                        index < itemCount - indicatorCount + 1

            PageIndicatorView(
                isSelected = isSelected,
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                defaultRadius = defaultRadius,
                selectedLength = selectedLength,
                animationDurationInMillis = animationDurationInMillis,
            )
            Spacer(modifier = Modifier.width(space))
        }
    }
}

@Composable
fun PageIndicatorView(
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
) {

    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )

    Canvas(
        modifier = modifier
            .size(
                width = width,
                height = defaultRadius,
            ),
    ) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = defaultRadius.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = defaultRadius.toPx(),
                y = defaultRadius.toPx(),
            ),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true)
@Composable
fun PageIndicatorPreview() {
    Dacs3shoesshopandroidTheme {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            PagerIndicator(pagerState = rememberPagerState(pageCount = { 4 }))
        }
    }
}
