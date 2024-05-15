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
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun MyOrderScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),

    ) {
        TopTitleBar(name = "My Orders", modifier = Modifier.padding(top = 16.dp))

        TabScreen()


    }

}

@Preview
@Composable
fun ProductScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            MyOrderScreen()
        }
    }
}