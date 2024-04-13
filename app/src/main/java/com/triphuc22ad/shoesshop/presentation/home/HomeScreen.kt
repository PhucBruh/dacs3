package com.triphuc22ad.shoesshop.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.home.components.BrandItem
import com.triphuc22ad.shoesshop.presentation.home.components.SectionHeader
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {

        },
        bottomBar = {

        },
        content = { scaffoldPadding ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SectionHeader(name = "Special Offers", onSeeAllClick = { /*TODO*/ })
                FlowRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..10) {
                        BrandItem(name = "Brand $i", logo = R.drawable.nike_logo, onClick = {})
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    Dacs3shoesshopandroidTheme {
        HomeScreen()
    }
}