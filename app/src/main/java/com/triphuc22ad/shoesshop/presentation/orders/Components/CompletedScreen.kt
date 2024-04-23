package com.triphuc22ad.shoesshop.presentation.orders.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.presentation.cart.components.CartItem
import com.triphuc22ad.shoesshop.util.component.OptionSwipeableContainer

@Composable
fun CompletedScreen() {
    var isUpdateStatus by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {


            items(4) {
                Row(Modifier.padding(vertical = 8.dp)) {
                    CardOrderItem(status = "Completed", action = "leave a review" ,onClick = { isUpdateStatus = true })
                }
            }

            items(10) {
                Row(Modifier.padding(vertical = 8.dp)) {
                    CardOrderItem(status = "Completed", action = "buy again" ,onClick = {})
                }
            }

        }

        OptionSwipeableContainer(
            active = isUpdateStatus,
            name = "Leave a Review",
            onSwipeDown = { isUpdateStatus = false },
            firstActionName = "Cancel",
            onFirstAction = { isUpdateStatus = false },
            secondActionName = "Submid",
            onSecondAction = { /*TODO*/ },
        ) {
            Column {
                CardReviewItem(
                    status = "complete",
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(2.dp, RoundedCornerShape(32.dp))
                )

            }
        }

    }
}



@Preview
@Composable
fun Preview(){
    CompletedScreen()
}