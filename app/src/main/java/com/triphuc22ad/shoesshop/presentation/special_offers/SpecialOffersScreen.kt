package com.triphuc22ad.shoesshop.presentation.special_offers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOfferBanner
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun SpecialOffersScreen(
    navigateToHome: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
    ) {
        TopTitleBar(name = "Special Offers", onLeftAction = navigateToHome)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(10) {
                SpecialOfferBanner(
                    specialOffer = SpecialOffer(
                        name = "Special Offer $it",
                        description = "Description $it",
                        value = 25,
                        img_url = "https://www.jordan1.vn/wp-content/uploads/2023/09/3022893_409.png_77c2f15034cf4fa3b92f07195024f407.png"
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SpecialOffersScreenPreview() {
    AppTheme {
        Surface {
            SpecialOffersScreen()
        }
    }
}
