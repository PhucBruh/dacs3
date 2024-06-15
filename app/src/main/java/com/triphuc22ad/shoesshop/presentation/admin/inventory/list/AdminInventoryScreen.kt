package com.triphuc22ad.shoesshop.presentation.admin.inventory.list

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.ui.theme.BgColor

@Composable
fun AdminInventoryScreen(
    appViewModel: AppViewModel = hiltViewModel(),
    adminInventoryViewModel: AdminInventoryViewModel = hiltViewModel(),
    navigateToAddInventory: () -> Unit,
    navigateToEditInventory: (Int) -> Unit,
) {
    val appState by appViewModel.state.collectAsState()
    val state = appState.adminInventoryUiState

    LaunchedEffect(Unit) {
        adminInventoryViewModel.fetchData()
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
                value = searchId,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                        searchId = newValue
                        adminInventoryViewModel.onEvent(
                            AdminInventoryEvent.ChangeQueryProductId(
                                newValue.toIntOrNull() ?: 0
                            )
                        )
                    }
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        if (searchId.isNotEmpty()) {
                            IconButton(onClick = {
                                adminInventoryViewModel.onEvent(
                                    AdminInventoryEvent.ChangeQueryProductId(
                                        0
                                    )
                                )
                                searchId = ""
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
                    adminInventoryViewModel.onEvent(AdminInventoryEvent.ChangeQuery(it))
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
                                adminInventoryViewModel.onEvent(AdminInventoryEvent.ChangeQuery(""))
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
            items(state.inventoryList) {
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
                            AsyncImage(
                                model = it.productImg,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(start = 12.dp)
                    ) {
                        Text(
                            "ID: ${it.productId}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "ID: ${it.id} - Name: ${it.productName}",
                            fontSize = 14.sp,
                        )

                        Text(
                            text = "${it.color.name} - ${it.size.size}",
                            fontSize = 14.sp,
                        )

                        Text(
                            "Stock: ${it.stock}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { navigateToEditInventory(it.id) }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        enabled = state.page > 0,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Previous", fontSize = 12.sp)
                    }
                    Text("Page ${if (state.totalPage != 0) state.page + 1 else 0} of ${state.totalPage}")
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


    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        SmallFloatingActionButton(
            onClick = { navigateToAddInventory() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .padding(end = 40.dp)
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }
}