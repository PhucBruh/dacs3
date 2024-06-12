package com.triphuc22ad.shoesshop.presentation.cart.screen

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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.presentation.cart.CartEvent
import com.triphuc22ad.shoesshop.presentation.cart.CartViewModel
import com.triphuc22ad.shoesshop.presentation.cart.components.CardAddress
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.cart.components.CardOrder
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun CheckOutScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    appViewModel: AppViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state = cartViewModel.state.collectAsState()
    val appState = appViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.validateCart()
    }

    if (appState.value.cartItems.isEmpty() && state.value.isCreatedOrder) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)
        ) {
            TopTitleBar(
                name = "Checkout", modifier = Modifier.padding(top = 16.dp),
                onLeftAction = navigateBack
            )
            Text(text = "Create success")
        }
    } else if (appState.value.cartItems.isEmpty() && !state.value.isCreatedOrder) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)
        ) {
            TopTitleBar(
                name = "Checkout", modifier = Modifier.padding(top = 16.dp),
                onLeftAction = navigateBack
            )
            Text(text = "The cart is empty now")
        }
    } else {
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
                    TopTitleBar(
                        name = "Checkout", modifier = Modifier.padding(top = 16.dp),
                        onLeftAction = navigateBack
                    )
                }

                item {
                    SectionHeader(name = "Orders")
                }

                items(appState.value.cartItems) {
                    Row(Modifier.padding(vertical = 8.dp)) {
                        CardOrder(
                            cartItem = it,
                            modifier = Modifier
                                .shadow(2.dp, RoundedCornerShape(32.dp))
                        )
                    }
                }

                item {
                    HorizontalDivider(Modifier.padding(top = 8.dp))
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 40.dp)
                    ) {
                        SectionHeader(name = "Total price")
                        val totalPrice = appState.value.cartItems.sumOf {
                            if (it.promotionPrice != 0.0) {
                                it.promotionPrice * it.quantity
                            } else {
                                it.price * it.quantity
                            }
                        }

                        Text(
                            text = "$totalPrice vnđ",
                            fontWeight = FontWeight.Bold
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
                    .background(
                        Color.White,
                        RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
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
                        onClick = { cartViewModel.onEvent(CartEvent.CreateOrder) },
                        modifier = Modifier
                            .size(height = 52.dp, width = 450.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = "Create order", fontSize = 16.sp)
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

@Preview
@Composable
fun CheckOutScreenPreview() {
    AppTheme {
        Surface {
            CheckOutScreen()
        }
    }
}