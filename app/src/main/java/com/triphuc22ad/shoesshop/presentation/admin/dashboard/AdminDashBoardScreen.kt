package com.triphuc22ad.shoesshop.presentation.admin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminDashBoardViewModel @Inject constructor() : ViewModel() {

}

@Composable
fun AdminDashBoardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HeaderItem(
                        category = "EARNING (MONTHLY)",
                        value = "200$",
                        color = Color.Blue,
                        modifier = Modifier.weight(0.48f)
                    )
                    Spacer(modifier = Modifier.weight(0.04f))
                    HeaderItem(
                        category = "EARNING (ANNUAL)",
                        value = "200$",
                        color = Color.Red,
                        modifier = Modifier.weight(0.48f)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {

                    HeaderItem(
                        category = "INCOMPLETE ORDER",
                        value = "200",
                        color = Color.Blue,
                        modifier = Modifier.weight(0.48f)
                    )
                    Spacer(modifier = Modifier.weight(0.04f))
                    HeaderItem(
                        category = "COMPLETED ORDER",
                        value = "200",
                        color = Color.Red,
                        modifier = Modifier.weight(0.48f)
                    )
                }
            }
        }


        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Analysis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                var month by remember { mutableStateOf("") }
                TextField(
                    value = month,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            month = newValue
                        }
                    },
                    singleLine = true,
                    label = { Text("Month") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(0.4f)
                )

                Spacer(modifier = Modifier.weight(0.05f))

                var year by remember { mutableStateOf("") }
                TextField(
                    value = year,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            year = newValue
                        }
                    },
                    singleLine = true,
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(0.4f)
                )
            }
        }

        item {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "View Analysis")
            }
        }

        if (true) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Result for 4-2024",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                ) {
                    val dataList = listOf(30, 8, 12, 23)
                    val floatValue = dataList.map { it.toFloat() / dataList.max().toFloat() }
                    val datesList = listOf(1, 2, 3, 4)

                    Box(contentAlignment = Alignment.BottomStart) {
                        BarGraph(
                            graphBarData = floatValue,
                            xAxisScaleData = datesList,
                            barData_ = dataList,
                            height = 300.dp,
                            roundType = BarType.TOP_CURVED,
                            barWidth = 30.dp,
                            barColor = Color(0xFF6fe349),
                            barArrangement = Arrangement.SpaceEvenly
                        )
                        Text(text = "Week", modifier = Modifier.padding(bottom = 18.dp))
                    }
                    Text(text = "Total earn: ")
                }
            }
        }
    }
}

@Composable
fun HeaderItem(
    category: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = category,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Text(
                text = value,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    AdminDashBoardScreen()
}