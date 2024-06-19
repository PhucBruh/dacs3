package com.triphuc22ad.shoesshop.presentation.admin.order.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.cart.components.CardOrder
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditOrderScreen(
    adminEditOrderViewModel: AdminEditOrderViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state by adminEditOrderViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        adminEditOrderViewModel.fetchData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Edit order", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            item {
                TextField(
                    value = state.orderToEdit.id.toString(),
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Id") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.orderToEdit.user.name,
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("User name") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.orderToEdit.user.phone,
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Phone") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.orderToEdit.shippingAddress,
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Shipping address") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.orderToEdit.description,
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = "${state.orderToEdit.price.toInt()} vnđ",
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Price") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            items(state.orderToEdit.details) {
                CardOrder(it)
            }

            item {
                var isExpandedStatusDropMenu by remember { mutableStateOf(false) }
                val statusList = listOf("PENDING", "PROCESSING", "SHIPPED", "COMPLETED")
                Box {
                    ExposedDropdownMenuBox(
                        expanded = isExpandedStatusDropMenu,
                        onExpandedChange = { isExpandedStatusDropMenu = it }) {
                        TextField(
                            value = state.orderToEdit.status,
                            onValueChange = {},
                            label = { Text("Status") },
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedStatusDropMenu) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth() // Adjust padding if needed
                        )
                        ExposedDropdownMenu(
                            expanded = isExpandedStatusDropMenu,
                            onDismissRequest = { isExpandedStatusDropMenu = false }) {
                            statusList.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = it) },
                                    onClick = {
                                        adminEditOrderViewModel.changeStatus(it)
                                        isExpandedStatusDropMenu = false
                                    })
                            }
                        }
                    }
                }
            }

            item {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    onClick = { adminEditOrderViewModel.update() },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Update")
                }
            }
        }
    }
}