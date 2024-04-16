package com.triphuc22ad.shoesshop.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.home.components.BrandItem
import com.triphuc22ad.shoesshop.util.component.FilterOption
import com.triphuc22ad.shoesshop.util.component.ProductCard
import com.triphuc22ad.shoesshop.util.component.ProductSearchBar
import com.triphuc22ad.shoesshop.util.component.SectionHeader
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOffer
import com.triphuc22ad.shoesshop.presentation.home.components.UserBar
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            var isActivated by remember { mutableStateOf(false) }
            var query by remember { mutableStateOf("") }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                if (!isActivated) {
                    UserBar(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 8.dp)
                    )
                }
                ProductSearchBar(
                    active = isActivated,
                    query = query,
                    onActiveChange = { isActivated = !isActivated },
                    onClear = { isActivated = false },
                    onFilterList = {},
                    modifier = Modifier
                        .padding(horizontal = if (!isActivated) 16.dp else 0.dp)
                )
            }
        },
    ) { scaffoldPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(12),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = scaffoldPadding.calculateTopPadding())
        ) {

            item(span = { GridItemSpan(12) }) {
                SectionHeader(
                    name = "Special Offers",
                    actionName = "See All",
                    onClick = { /*TODO*/ }
                )
            }

            item(span = { GridItemSpan(12) }) {
                SpecialOffer(shadow = true)
            }

            val listBrand = List(8) { "Brand$it" }
            items(items = listBrand, span = { GridItemSpan(3) }) {
                Column {
                }
                BrandItem(name = it, logo = R.drawable.nike_logo, onClick = {})
            }

            item(span = { GridItemSpan(12) }) {
                SectionHeader(
                    name = "Most Popular",
                    actionName = "See All",
                    onClick = { /*TODO*/ }
                )
            }

            item(span = { GridItemSpan(12) }) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(10) {
                        FilterOption(text = "Filter$it", onClick = {})
                    }
                }
            }

            items(count = 10, span = { GridItemSpan(6) }) {
                ProductCard(image = R.drawable.curry_6)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            HomeScreen()
        }
    }
}