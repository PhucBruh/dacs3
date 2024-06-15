package com.triphuc22ad.shoesshop.presentation.product

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.presentation.components.FilterOption
import com.triphuc22ad.shoesshop.presentation.components.OptionSwipeableContainer
import com.triphuc22ad.shoesshop.presentation.components.ProductCard
import com.triphuc22ad.shoesshop.presentation.util.formatPrice
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
    appViewModel: AppViewModel = hiltViewModel(),
    navigateToProductDetail: (Int) -> Unit = {},
) {

    val appState by appViewModel.state.collectAsState()
    val state = appState.productListUiState

    var isFilterVisible by remember { mutableStateOf(false) }
    val filter = remember {
        mutableStateOf(state.filter)
    }

    LaunchedEffect(Unit) {
        productViewModel.fetchData()
    }

    Box(modifier = Modifier) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color(0XFF101921))
                    .padding(top = 60.dp)
                    .padding(horizontal = 16.dp)
            ) {
                TextField(
                    value = state.query,
                    onValueChange = { productViewModel.onEvent(ProductEvent.ChangeQuery(it)) },
                    leadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = ""
                            )
                        }
                    },
                    trailingIcon = {
                        Row(horizontalArrangement = Arrangement.End) {
                            if (state.query.isNotEmpty()) {
                                IconButton(onClick = {
                                    productViewModel.onEvent(
                                        ProductEvent.ChangeQuery(
                                            ""
                                        )
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = ""
                                    )
                                }
                            }
                            IconButton(onClick = { isFilterVisible = true }) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = ""
                                )
                            }
                        }
                    },
                    placeholder = { Text(text = "Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 140.dp)
        ) {

            LazyVerticalGrid(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(state.productList) {
                    ProductCard(
                        product = it,
                        onClick = { navigateToProductDetail(it.id) }
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {},
                            enabled = state.page > 0,
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(text = "Previous", fontSize = 12.sp)
                        }
                        Text("Page ${if (state.page != 0) state.page + 1 else 0} of ${state.totalPage}")
                        Button(
                            onClick = {},
                            enabled = state.page + 1 < state.totalPage,
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text("Next", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        OptionSwipeableContainer(
            name = "Sort & Filter",
            active = isFilterVisible,
            onSwipeDown = {
                if (!state.applyFilter) {
                    filter.value = ProductListFilter()
                }
                isFilterVisible = false
            },
            firstActionName = "Reset",
            onFirstAction = {
                productViewModel.resetFilter()
                filter.value = ProductListFilter()
            },
            secondActionName = "Apply",
            onSecondAction = {
                productViewModel.applyFilter(filter = filter.value)
            },
//        paddingBottom = 100.dp
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(400.dp)
            ) {
                item {
                    FilterSection(sectionName = "Special offer") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            val saleStatusList = listOf(
                                "NORMAL",
                                "ACTIVE",
                                "INACTIVE"
                            )
                            items(saleStatusList) { status ->
                                FilterOption(
                                    text = status,
                                    onClick = {
                                        filter.value = filter.value.copy(
                                            saleStatus = status
                                        )
                                    },
                                    active = filter.value.saleStatus == status
                                )
                            }
                        }
                    }
                }

                item {
                    FilterSection(sectionName = "order by") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            val orderByList = listOf(
                                "NAME",
                                "SOLD",
                                "RATING",
                                "PRICE"
                            )
                            items(orderByList) { orderBy ->
                                FilterOption(
                                    text = orderBy,
                                    onClick = {
                                        filter.value = filter.value.copy(orderBy = orderBy)
                                    },
                                    active = filter.value.orderBy == orderBy
                                )
                            }
                        }
                    }
                }

                item {
                    val sortByList = listOf(
                        "ASCENDING",
                        "DESCENDING"
                    )
                    FilterSection(sectionName = "Sort by") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(sortByList) { sortBy ->
                                FilterOption(
                                    text = sortBy,
                                    onClick = {
                                        filter.value = filter.value.copy(sortBy = sortBy)
                                    },
                                    active = filter.value.sortBy == sortBy
                                )
                            }
                        }
                    }
                }

                item {
                    FilterSection(sectionName = "Price") {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {

                            var sliderPosition by remember {
                                mutableStateOf(0f..5000000f)
                            }
                            val step = 100000
                            RangeSlider(
                                value = filter.value.minPrice.toFloat()..filter.value.maxPrice.toFloat(),
                                steps = 0,
                                onValueChange = { range ->
                                    val start = (range.start / step).toInt() * step
                                    val end = (range.endInclusive / step).toInt() * step
                                    sliderPosition = start.toFloat()..end.toFloat()
                                },
                                valueRange = 0f.rangeTo(5000000f),
                                onValueChangeFinished = {
                                    filter.value = filter.value.copy(
                                        minPrice = sliderPosition.start.toDouble(),
                                        maxPrice = sliderPosition.endInclusive.toDouble()
                                    )
                                },
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.Black,
                                    activeTrackColor = Color.Black,
                                    inactiveTrackColor = Color.Gray
                                ),
                            )

                            Text(
                                text = formatPrice(filter.value.minPrice) + " - " + formatPrice(
                                    filter.value.maxPrice
                                ),
                                modifier = Modifier.padding(top = 16.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterOptionList(
    visible: Boolean = false,
    onSwipeDown: () -> Unit,
) {

    OptionSwipeableContainer(
        name = "Sort & Filter",
        active = visible,
        onSwipeDown = onSwipeDown,
        firstActionName = "Reset",
        onFirstAction = {},
        secondActionName = "Apply",
        onSecondAction = {},
//        paddingBottom = 100.dp
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(400.dp)
        ) {
            item {
                FilterSection(sectionName = "Categories") {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(4) {
                            FilterOption(text = "Filter $it", onClick = {

                            })
                        }
                    }
                }
            }

            item {
                FilterSection(sectionName = "Gender") {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(4) {
                            FilterOption(text = "Gender $it", onClick = {})
                        }
                    }
                }
            }

            item {
                FilterSection(sectionName = "Sort By") {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(4) {
                            FilterOption(text = "Sort $it", onClick = {})
                        }
                    }
                }
            }
            item {
                FilterSection(sectionName = "Rating") {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(4) {
                            FilterOption(text = "★  $it", onClick = {})
                        }
                    }
                }
            }
            item {
                FilterSection(sectionName = "Price") {
                    RangeSliderExample()
                }
            }
        }
    }

}

@Composable
private fun FilterSection(
    sectionName: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = sectionName, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        content()
    }
}

@Composable
fun RangeSliderExample() {
    var sliderPosition by remember { mutableStateOf(0f..100f) }

    fun formatCurrency(value: Float): String {
        return "$" + String.format("%.2f", value)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        RangeSlider(
            value = sliderPosition,
            steps = 0,
            onValueChange = { range ->
                sliderPosition = range
            },
            valueRange = 0f.rangeTo(100f),
            onValueChangeFinished = {
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.Black,
                inactiveTrackColor = Color.Gray
            ),
        )

        Text(
            text = formatCurrency(sliderPosition.start) + " - " + formatCurrency(sliderPosition.endInclusive),
            modifier = Modifier.padding(top = 16.dp),
            color = Color.Black
        )
    }
}


@Preview
@Composable
fun ProductScreenPreview() {
    AppTheme {
        Surface {
            ProductScreen()
        }
    }
}
