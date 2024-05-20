package com.triphuc22ad.shoesshop.presentation.cart.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.presentation.cart.components.VoucherItem
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun ChoosePromoScreen() {

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)
        ) {

            item {
                    TopTitleBar(name = " Add Promo", modifier = Modifier.padding(top = 16.dp))
            }

            items(5) {
                Row(Modifier.padding(vertical = 8.dp)) {
                    VoucherItem(
                        modifier = Modifier
                            .shadow(2.dp, RoundedCornerShape(32.dp))
                    )
                }
            }

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .border(
                    border = BorderStroke(2.dp, Color.Gray),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(16.dp)
        ) {
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = {},
                    modifier = Modifier
                        .size(height = 52.dp, width = 450.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(text = "Apply", fontSize = 17.sp)
                    }
                }
            }


        }
    }
}


@Preview
@Composable
fun AddPromoPrivew() {
    AppTheme {
        Surface {
            ChoosePromoScreen()
        }
    }
}