package com.triphuc22ad.shoesshop.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.presentation.home.components.OptionFilterList
import com.triphuc22ad.shoesshop.presentation.home.components.ProductCardList
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
            Column(
                Modifier
                    .padding(top = 12.dp)
                    .fillMaxSize()
            ) {
                SectionHeader(
                    name = "Most popular", onSeeAllClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 4.dp)
                )
                OptionFilterList(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                )
                ProductCardList(Modifier.padding(horizontal = 16.dp))
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