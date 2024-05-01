package com.triphuc22ad.shoesshop.presentation.product

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.util.component.ProductSearchBar
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.FilterOption
import com.triphuc22ad.shoesshop.util.component.OptionSwipeableContainer
import com.triphuc22ad.shoesshop.util.component.ProductCard
import com.triphuc22ad.shoesshop.util.component.SectionHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@Composable
fun ProductScreen() {
    var isFilterVisible by remember { mutableStateOf(false) }
    var isSearchBarActivated by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(if (!isSearchBarActivated) 16.dp else 0.dp)
            ) {
                ProductSearchBar(
                    query = "",
                    active = isSearchBarActivated,
                    onActiveChange = { isSearchBarActivated = it },
                    onClear = { isSearchBarActivated = false },
                    onFilterList = { isFilterVisible = true },
                )
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                SectionHeader(
                    name = """Results for "Running" """,
                    actionName = "12.483 founds",
                    onClick = { /*TODO*/ })

                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(14) {
                        ProductCard(image = R.drawable.curry_6)
                    }
                }
            }
            FilterOptionList(
                visible = isFilterVisible,
                onSwipeDown = { isFilterVisible = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterOptionList(
    visible: Boolean = false,
    onSwipeDown: () -> Unit
) {

    OptionSwipeableContainer(
        name = "Sort & Filter",
        active = visible,
        onSwipeDown = onSwipeDown,
        firstActionName = "Reset",
        onFirstAction = {},
        secondActionName = "Apply",
        onSecondAction = {}
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            FilterSection(sectionName = "Categories") {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(4) {
                        FilterOption(text = "Filter $it", onClick = {})
                    }
                }
            }
            FilterSection(sectionName = "Gender") {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(4) {
                        FilterOption(text = "Gender $it", onClick = {})
                    }
                }
            }
            FilterSection(sectionName = "Price Range") {

            }
            FilterSection(sectionName = "Sort By") {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(4) {
                        FilterOption(text = "Sort $it", onClick = {})
                    }
                }
            }
            FilterSection(sectionName = "Rating") {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(4) {
                        FilterOption(text = "★  $it", onClick = {})
                    }
                }
            }
        }
    }

}

@Composable
private fun FilterSection(
    sectionName: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = sectionName, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        content()
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProductScreen()
        }
    }
}
