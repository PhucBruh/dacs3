package com.triphuc22ad.shoesshop.presentation.product

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.presentation.components.ProductSearchBar
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.components.FilterOption
import com.triphuc22ad.shoesshop.presentation.components.OptionSwipeableContainer
import com.triphuc22ad.shoesshop.presentation.components.ProductCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
    appViewModel: AppViewModel = hiltViewModel(),
    navigateToProductDetail: (Int) -> Unit = {},
) {
    val state by productViewModel.state.collectAsState()
    val appState by appViewModel.state.collectAsState()

    var isFilterVisible by remember { mutableStateOf(false) }
    var isSearchBarActivated by remember { mutableStateOf(false) }
    Box(modifier = Modifier) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(if (!isSearchBarActivated) 16.dp else 0.dp)
        ) {
            ProductSearchBar(
                query = appState.productFilter.query,
                active = isSearchBarActivated,
                onActiveChange = { isSearchBarActivated = it },
                onClear = { isSearchBarActivated = false },
                onFilterList = { isFilterVisible = true },
            )
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
            }
        }
        FilterOptionList(
            visible = isFilterVisible,
            onSwipeDown = { isFilterVisible = false }
        )
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
                            FilterOption(text = "Filter $it", onClick = {})
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
                // Khi hoàn tất sự thay đổi giá trị, bạn có thể thực hiện các logic kinh doanh ở đây
                // viewModel.updateSelectedSliderValue(sliderPosition)
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
