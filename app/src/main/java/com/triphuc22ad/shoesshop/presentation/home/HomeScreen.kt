package com.triphuc22ad.shoesshop.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import com.triphuc22ad.shoesshop.presentation.components.ProductCard
import com.triphuc22ad.shoesshop.presentation.components.SectionHeader
import com.triphuc22ad.shoesshop.presentation.home.components.BrandItem
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOfferBanner
import com.triphuc22ad.shoesshop.presentation.home.components.UserBar
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToWishlist: () -> Unit = {},
    navigateToProduct: () -> Unit = {},
    navigateToProductDetail: (Int) -> Unit = {},
    navigateToSpecialOffer: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)

            ) {
                UserBar(
                    onWishListClick = navigateToWishlist,
                    onUserClick = navigateToProfile,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
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
                    onClick = navigateToSpecialOffer
                )
            }


            item(span = { GridItemSpan(12) }) {
                SpecialOfferBanner(
                    specialOffer = SpecialOffer(
                        name = "Special Offer 1",
                        description = "Description 1",
                        value = 25,
                        img_url = "https://www.jordan1.vn/wp-content/uploads/2023/09/3022893_409.png_77c2f15034cf4fa3b92f07195024f407.png"
                    ),
                    onClick = { navigateToProductDetail(1) }
                )
            }

            val listBrand = List(8) { "Brand$it" }
            items(items = state.listBrand, span = { GridItemSpan(3) }) {
                BrandItem(brand = it, onClick = { navigateToProduct() })
            }

            item(span = { GridItemSpan(12) }) {
                SectionHeader(name = "Most Popular", actionName = "", onClick = {})
            }

            val listPopularProduct = List(8) {
                Product(
                    id = it,
                    name = "Product 1",
                    description = "",
                    rating = 4.5f,
                    price = 100000.0,
                    totalSold = 100,
                    brand = "Nike",
                    img_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEc4NH68my5V52slAx1cyss86EyNs3wUXOaXt1nG05hQ&s",
                    isFavorite = false,
                    colors = listOf(
                        Pair("Red", "F00000"),
                    ),
                    sizes = listOf(41, 42, 43)
                )
            }
            items(items = listPopularProduct, span = { GridItemSpan(6) }) {
                ProductCard(product = it,
                    onClick = { it.id?.let { it1 -> navigateToProductDetail(it1) } }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        Surface {
            HomeScreen()
        }
    }
}