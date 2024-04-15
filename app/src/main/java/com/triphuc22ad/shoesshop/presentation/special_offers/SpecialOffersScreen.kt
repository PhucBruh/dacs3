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
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOffer
import com.triphuc22ad.shoesshop.util.component.BackBar
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun SpecialOffersScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
    ) {
        BackBar(name = "Special Offers", onBack = { /*TODO*/ }) { }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(10) {
                SpecialOffer()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SpecialOffersScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            SpecialOffersScreen()
        }
    }
}
