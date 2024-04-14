package com.triphuc22ad.shoesshop.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.home.components.BrandItem
import com.triphuc22ad.shoesshop.presentation.home.components.OptionFilter
import com.triphuc22ad.shoesshop.presentation.home.components.ProductCard
import com.triphuc22ad.shoesshop.presentation.home.components.SectionHeader
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOffer
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(12),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        item(span = { GridItemSpan(12) }) {
            SectionHeader(
                name = "Special Offers",
                onSeeAllClick = { /*TODO*/ }
            )
        }

        item(span = { GridItemSpan(12) }) {
            SpecialOffer()
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
                onSeeAllClick = { /*TODO*/ }
            )
        }

        item(span = { GridItemSpan(12) }) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(10) {
                    OptionFilter(text = "Filter$it", onClick = {})
                }
            }
        }

        items(count = 10, span = { GridItemSpan(6) }) {
            ProductCard(image = R.drawable.curry_6)
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