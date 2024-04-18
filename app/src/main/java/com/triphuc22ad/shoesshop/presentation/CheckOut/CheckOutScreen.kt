package com.triphuc22ad.shoesshop.presentation.CheckOut

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.CardOrder
import com.triphuc22ad.shoesshop.util.component.CartAddress
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun CheckOutScreen() {

    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 5.dp)
                .padding(bottom = 100.dp)
        ) {
            TopTitleBar(name = "Checkout")

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(200.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Shipping Address",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                CartAddress()

                HorizontalDivider()
            }


            Column() {
                Text(
                    text = "Order List",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    items(10) {
                        CardOrder(
                            modifier = Modifier
                                .shadow(2.dp, RoundedCornerShape(32.dp))
                                .padding(top = 4.dp)
                                .padding(bottom = 4.dp)
                        )
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                        ) {
                            HorizontalDivider()

                            Text(
                                text = "Shipping Address",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                            )


                        }
                    }


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
                    Row(verticalAlignment = Alignment.CenterVertically,)
                    {
                        Text(text = "Continue to Payment", fontSize = 17.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

            }


        }
    }
}

@Preview
@Composable
fun PreviewCheckOut(){
    Dacs3shoesshopandroidTheme {
        Surface {
            CheckOutScreen()
        }
    }
}