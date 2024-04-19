package com.triphuc22ad.shoesshop.presentation.check_out

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.presentation.check_out.components.CardAddress
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.presentation.check_out.components.CardOrder
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun CheckOutScreen() {

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
                TopTitleBar(name = "Checkout", modifier = Modifier.padding(top = 16.dp))
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SectionHeader(name = "Shipping address")

                    CardAddress()

                    HorizontalDivider(Modifier.padding(top = 8.dp))
                }
            }

            item {
            }

            item {
                SectionHeader(name = "Orders")
            }

            items(10) {
                Row(Modifier.padding(vertical = 8.dp)) {
                    CardOrder(
                        modifier = Modifier
                            .shadow(2.dp, RoundedCornerShape(32.dp))
                    )
                }
            }

            item {
                HorizontalDivider(Modifier.padding(top = 8.dp))
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SectionHeader(name = "Choose Shipping")

                    // Edit the column bellow to the component you want
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(2.dp, RoundedCornerShape(20.dp))
                            .padding(vertical = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = "Rating",
                            tint = Color.Black,
                        )

                        Text(
                            text = "Choose Shipping Type",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                        )

                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "Rating",
                            tint = Color.Black,
                        )

                    }


                    HorizontalDivider(Modifier.padding(top = 8.dp))
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 40.dp)
                ) {
                    SectionHeader(name = "Promote Code")

                    // Edit the column bellow to the component you want
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            InputCode(
                                value = "",
                                onValueChange = {},
                                description = "Enter Promo Code",

                                )

                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.Gray, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color.Black,
                                )
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .padding(vertical = 20.dp, horizontal = 20.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Amount",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$585.00",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Shipping",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "-",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            HorizontalDivider(Modifier.padding(top = 8.dp, bottom = 8.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Total",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "-",
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
                    Row(verticalAlignment = Alignment.CenterVertically)
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

@Composable
private fun SectionHeader(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun InputCode(
    value: String,
    onValueChange: (String) -> Unit,
    description: String,
    modifier: Modifier = Modifier
        .background(color = Color.White),
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = description)
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewCheckOut() {
    Dacs3shoesshopandroidTheme {
        Surface {
            CheckOutScreen()
        }
    }
}