package com.triphuc22ad.shoesshop.presentation.admin.order.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.presentation.util.limitText
import com.triphuc22ad.shoesshop.ui.theme.BgColor

@Composable
fun AdminOrderScreen(
    appViewModel: AppViewModel = hiltViewModel(),
    adminOrderViewModel: AdminOrderViewModel = hiltViewModel(),
    navigateToEditOrder: (Int) -> Unit = {},
) {
    val appState by appViewModel.state.collectAsState()
    val state = appState.adminOrderUiState

    LaunchedEffect(Unit) {
        adminOrderViewModel.fetchData()
    }

    Column(Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            var searchId by remember { mutableStateOf("") }

            TextField(
                value = "hello",
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        searchId = newValue
                    }
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        if (searchId.isNotEmpty()) {
                            IconButton(onClick = {
                            }, modifier = Modifier.size(14.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                },
                leadingIcon = {
                    IconButton(onClick = {}, Modifier.size(14.dp)) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("ID") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(0.4f)
                    .clip(RoundedCornerShape(20.dp))
            )

            TextField(
                value = state.searchInfo,
                onValueChange = {
                },
                leadingIcon = {
                    IconButton(onClick = {}, Modifier.size(14.dp)) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        if (state.searchInfo.isNotEmpty()) {
                            IconButton(onClick = {
                            }, modifier = Modifier.size(14.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                },
                label = { Text("Search") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(0.6f)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        LazyColumn {
            items(state.orderList) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(BgColor, RoundedCornerShape(32.dp))
                        .fillMaxWidth(),
                ) {
                    Column(
                        Modifier
                            .padding(vertical = 10.dp)
                            .padding(start = 5.dp)
                            .shadow(2.dp, RoundedCornerShape(32.dp))
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.White, RoundedCornerShape(32.dp))
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (it.status == "COMPLETED"
                                    ) R.drawable.completed_order else R.drawable.incompleted_order
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(start = 12.dp)
                    ) {

                        Text(
                            "ID: ${it.id}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = limitText(it.shippingAddress, 16),
                            fontSize = 16.sp,
                        )

                        Text(
                            text = limitText(it.description, 16),
                            fontSize = 16.sp,
                        )

                        Text(
                            "${it.price.toInt()} vnđ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { navigateToEditOrder(it.id) }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit")
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        enabled = state.page > 0,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Previous", fontSize = 12.sp)
                    }
                    Text("Page ${state.page + 1} of ${if (state.totalPage != 0) state.totalPage else 1} ")
                    Button(
                        onClick = {},
                        enabled = state.page + 1 < state.totalPage,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Next", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun AdminOrderCard(
    order: OrderInfo,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Column(
                Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 5.dp)
                    .shadow(2.dp, RoundedCornerShape(32.dp))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .background(BgColor, RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(
                            id = if (order.status == "COMPLETED"
                            ) R.drawable.completed_order else R.drawable.incompleted_order
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "ID: ${order.id}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = limitText("Address: ${order.shippingAddress}", maxLength = 16),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = limitText("Description: ${order.description}", maxLength = 16),
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.status,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.clickable { onEditClick() }
                )
//
//                Icon(
//                    Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.Black,
//                    modifier = Modifier.clickable { onDeleteClick() }
//                )
            }
        }
    }
}
