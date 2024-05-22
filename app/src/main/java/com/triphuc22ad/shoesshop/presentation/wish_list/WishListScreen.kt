package com.triphuc22ad.shoesshop.presentation.wish_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.presentation.components.ProductCard
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun WishListScreen(
    navigateBack: () -> Unit = {},
    navigateToProductDetail: (Int) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
    ) {
        TopTitleBar(name = "My Wishlist", onLeftAction = navigateBack)

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(14) {
                ProductCard(
                    product = Product(
                        name = "Product 1",
                        description = "",
                        rating = 4.5f,
                        price = 100000.0,
                        totalSold = 100,
                        brand = "Nike",
                        img_url = "https://www.jordan1.vn/wp-content/uploads/2023/09/3022893_409.png_77c2f15034cf4fa3b92f07195024f407.png",
                        isFavorite = false,
                        colors = listOf(
                            Pair("Red", "FF0000"),
                        ),
                        sizes = listOf(41, 42, 43)
                    ),
                    onClick = { navigateToProductDetail(it) }
                )
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
