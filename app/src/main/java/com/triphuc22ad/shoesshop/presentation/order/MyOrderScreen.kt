package com.triphuc22ad.shoesshop.presentation.order

import TabScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun MyOrderScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
        ) {
            TopTitleBar(
                leftIconAction = null,
                name = "My Orders",
            )
        }

        TabScreen()
    }

}

@Preview
@Composable
fun ProductScreenPreview() {
    AppTheme {
        Surface {
            MyOrderScreen()
        }
    }
}