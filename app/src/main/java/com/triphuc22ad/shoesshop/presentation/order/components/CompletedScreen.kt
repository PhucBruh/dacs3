package com.triphuc22ad.shoesshop.presentation.order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.presentation.auth.login.components.CustomTextField
import com.triphuc22ad.shoesshop.presentation.components.OptionSwipeableContainer

@Composable
fun CompletedScreen() {
    var isUpdateStatus by remember { mutableStateOf(false) }
    var rating by remember { mutableDoubleStateOf(1.5) }

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

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "How is your order?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        )

                    Text(
                        text = "Please give your rating & also your order",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )

                    RatingBar(
                        modifier = Modifier
                            .size(50.dp),
                        rating = rating,
                        onRatingChanged = {
                            rating = it
                        },
                        starsColor = Color.Black
                    )

                    CustomTextField(
                        description = "product reviews",
                        icon = Icons.Default.Comment,
                        value = "",
                        onValueChange = {}
                    )

                }

            }
        }

    }
}

@Preview
@Composable
fun Preview(){
   CompletedScreen()
}