package com.triphuc22ad.shoesshop.presentation.cart

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
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.CartItem
import com.triphuc22ad.shoesshop.presentation.cart.components.CartItem
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import com.triphuc22ad.shoesshop.presentation.components.OptionSwipeableContainer
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar


@Composable
fun CartScreen2() {
    var isRemoveActivated by remember { mutableStateOf(false) }

    val cartItem = CartItem(
        name = "Curry 6",
        price = 200,
        quantity = 4,
        color = Pair("Black", Color.Black),
        imageId = R.drawable.curry_6,
        size = 42
    )

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
            TopTitleBar(name = "My Cart", leftIconAction = Icons.Default.ShoppingBag)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(10) {
                    CartItem(
                        item = cartItem,
                        onDelete = { isRemoveActivated = true },
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
                Text(text = "$555.555", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { /*TODO*/ },
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

        OptionSwipeableContainer(
            active = isRemoveActivated,
            name = "Remove From Cart ?",
            onSwipeDown = { isRemoveActivated = false },
            firstActionName = "Cancel",
            onFirstAction = { isRemoveActivated = false },
            secondActionName = "Yes, Remove",
            onSecondAction = { /*TODO*/ },
        ) {
            CartItem(
                item = cartItem,
                removable = false,
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(2.dp, RoundedCornerShape(32.dp)),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartScreen2Preview() {
    AppTheme {
        Surface {
            CartScreen2()
        }
    }
}
