package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@Composable
fun AdminEditInventoryScreen(
    adminEditInventoryViewModel: AdminEditInventoryViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state by adminEditInventoryViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Edit inventory", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = { navigateBack() }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {

            item {
                TextField(
                    value = state.inventoryToEdit.id.toString(),
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
                    value = state.inventoryToEdit.productId.toString(),
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Product Id") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.inventoryToEdit.productName,
                    onValueChange = {},
                    singleLine = true,
                    enabled = false,
                    label = { Text("Product name") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = "${state.inventoryToEdit.color.name} - ${state.inventoryToEdit.color.value}",
                    onValueChange = {},
                    singleLine = true,
                    enabled = false,
                    label = { Text("Color") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = "${state.inventoryToEdit.size.size}",
                    onValueChange = {},
                    singleLine = true,
                    enabled = false,
                    label = { Text("Size") },
                    modifier = Modifier
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                var stockToEdit by remember { mutableStateOf(state.inventoryToEdit.stock.toString()) }
                LaunchedEffect(state.inventoryToEdit.stock) {
                    stockToEdit = state.inventoryToEdit.stock.toString()
                }
                TextField(
                    value = stockToEdit,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            stockToEdit = newValue
                            adminEditInventoryViewModel.onEvent(
                                AdminEditInventoryEvent.ChangeStock(
                                    newValue.toIntOrNull() ?: 0
                                )
                            )
                        }
                    },
                    label = { Text("Stock") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
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
                    Text(text = "Update stock")
                }
            }
        }
    }
}