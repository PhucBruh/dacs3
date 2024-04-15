package com.triphuc22ad.shoesshop.presentation.wish_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.util.component.BackBar
import com.triphuc22ad.shoesshop.util.component.FilterOption
import com.triphuc22ad.shoesshop.util.component.ProductCard

@Composable
fun WishListScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
    ) {
        BackBar(name = "My Wishlist", onBack = { /*TODO*/ }) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(28.dp)) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(10) {
                FilterOption(text = "Filter$it", onClick = {})
            }
        }
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
}

@Preview
@Composable
fun WishListScreenPreview() {
    MaterialTheme {
        Surface {
            WishListScreen()
        }
    }
}
