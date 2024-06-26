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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.triphuc22ad.shoesshop.presentation.cart.components.CartItem
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.presentation.util.formatPrice
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun CartScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    appViewModel: AppViewModel = hiltViewModel(),
    navigateToCheckout: () -> Unit = {},
) {
    val state by cartViewModel.state.collectAsState()
    val appState by appViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.validateCart()
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp)
                .padding(bottom = 100.dp)
        ) {
            TopTitleBar(name = "My Cart", leftIconAction = null)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(appState.cartItems) {
                    CartItem(
                        item = it,
                        onIncrease = { cartViewModel.onEvent(CartEvent.IncreaseQuantity(it.productId)) },
                        onDecrease = { cartViewModel.onEvent(CartEvent.DecreaseQuantity(it.productId)) },
                        onDelete = { cartViewModel.onEvent(CartEvent.DeleteItem(it.productId)) },
                        modifier = Modifier
                            .shadow(2.dp, RoundedCornerShape(32.dp))
                            .padding(top = 4.dp)
                            .padding(bottom = 4.dp),
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
            Column {
                Text(text = "Total Price", fontSize = 12.sp, fontWeight = FontWeight.Light)
                val totalPrice = appState.cartItems.sumOf {
                    if (it.promotionPrice != 0.0) {
                        it.promotionPrice * it.quantity
                    } else {
                        it.price * it.quantity
                    }
                }
                Text(
                    text = formatPrice(totalPrice),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { cartViewModel.onEvent(CartEvent.CheckOut(navigateToCheckout)) },
                    modifier = Modifier.size(height = 52.dp, width = 200.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Checkout", fontSize = 17.sp)
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

@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    AppTheme {
        Surface {
            CartScreen()
        }
    }
}