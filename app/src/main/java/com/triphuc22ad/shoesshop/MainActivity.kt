package com.triphuc22ad.shoesshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.triphuc22ad.shoesshop.presentation.cart.CartScreen
import com.triphuc22ad.shoesshop.presentation.home.HomeScreen
import com.triphuc22ad.shoesshop.presentation.product.ProductScreen
import com.triphuc22ad.shoesshop.presentation.product_detail.ProductDetailScreen
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dacs3shoesshopandroidTheme {
                // A surface container using the 'background' color from the theme
                ProductDetailScreen()
            }
        }
    }
}