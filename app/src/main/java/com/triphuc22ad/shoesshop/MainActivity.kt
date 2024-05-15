package com.triphuc22ad.shoesshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.triphuc22ad.shoesshop.presentation.product_detail.ProductDetailScreen
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dacs3shoesshopandroidTheme {
                ProductDetailScreen()
            }
        }
    }
}