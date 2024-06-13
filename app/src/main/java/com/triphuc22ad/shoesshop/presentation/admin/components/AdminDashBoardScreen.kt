package com.triphuc22ad.shoesshop.presentation.admin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.presentation.order.components.LineChart

@Composable
fun AdminDashBoardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                CardOrderItem(category = "EARNNGS (MONTHLY)", quantity = "200$", color = Color.Blue)
                CardOrderItem(category = "EARNNGS (ANNUAL)", quantity = "200$", color = Color.Red)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                CardOrderItem(category = "PENDING ORDER", quantity = "200", color = Color.Magenta)
                CardOrderItem(category = "COMPLETED ORDER", quantity = "150", color = Color.Cyan)
            }
        }

        Column {

                val data = listOf(
                    Pair(6, 111.45),
                    Pair(7, 111.0),
                    Pair(8, 113.45),
                    Pair(9, 112.25),
                    Pair(10, 116.45),
                    Pair(11, 113.35),
                    Pair(12, 118.65),
                    Pair(13, 110.15),
                    Pair(14, 113.05),
                    Pair(15, 114.25),
                    Pair(16, 116.35),
                    Pair(17, 117.45),
                    Pair(18, 112.65),
                    Pair(19, 115.45),
                    Pair(20, 111.85)
                )

                Column(
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    LineChart(
                        data = data,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
            }
        }

        Column {
            DateRangePickerSample()
        }


    }
}

@Composable
fun CardOrderItem(
    category : String,
    quantity : String,
    color: Color
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(32.dp))
    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 7.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
            Text(
                text = category,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Text(
                text = quantity,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
//    Dacs3shoesshopandroidTheme {
//        Surface {
            AdminDashBoardScreen()
//        }
//    }
}