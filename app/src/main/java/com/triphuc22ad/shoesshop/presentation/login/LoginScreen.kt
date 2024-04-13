package com.triphuc22ad.shoesshop.presentation.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Login() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        SingUp()
    }
}

@Preview
@Composable
fun Preview(){
    Login()
}