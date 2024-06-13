package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAddInventoryScreen(
    adminAddInventoryViewModel: AdminAddInventoryViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state by adminAddInventoryViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Add new inventory", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            item {

                var productIdToEdit by remember { mutableStateOf("") }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = productIdToEdit,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                productIdToEdit = newValue
                                adminAddInventoryViewModel.onEvent(
                                    AdminAddInventoryEvent.ChangeProductId(
                                        newValue.toIntOrNull() ?: 0
                                    )
                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("Product Id") },
                        modifier = Modifier
                            //
                            .weight(0.7f)
                            .padding(end = 8.dp)
                    )
                    Button(onClick = { adminAddInventoryViewModel.onEvent(AdminAddInventoryEvent.CheckProduct) }) {
                        Text(text = "Check")
                    }
                }
            }

            item {
                var isExpandedStatusDropMenu by remember { mutableStateOf(false) }
                Box {
                    ExposedDropdownMenuBox(
                        expanded = isExpandedStatusDropMenu,
                        onExpandedChange = { isExpandedStatusDropMenu = it }) {
                        var selectedSize by remember { mutableStateOf("") }
                        TextField(
                            value = selectedSize,
                            onValueChange = {},
                            label = { Text("Size") },
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
                            state.sizeCheckList.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = "ID: ${it.id} - Size: ${it.size}") },
                                    onClick = {
                                        adminAddInventoryViewModel.onEvent(
                                            AdminAddInventoryEvent.ChangeSize(
                                                it
                                            )
                                        )
                                        selectedSize =
                                            "ID: ${it.id} - Size: ${it.size}"
                                        isExpandedStatusDropMenu = false
                                    })
                            }
                        }
                    }
                }
            }

            item {
                var isExpandedStatusDropMenu by remember { mutableStateOf(false) }
                Box {
                    ExposedDropdownMenuBox(
                        expanded = isExpandedStatusDropMenu,
                        onExpandedChange = { isExpandedStatusDropMenu = it }) {
                        var selectedColor by remember { mutableStateOf("") }
                        TextField(
                            value = selectedColor,
                            onValueChange = {},
                            label = { Text("Color") },
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedStatusDropMenu) },
                            modifier = Modifier
                                .menuAnchor()
                                //
                                .fillMaxWidth() // Adjust padding if needed
                        )
                        ExposedDropdownMenu(
                            expanded = isExpandedStatusDropMenu,
                            onDismissRequest = { isExpandedStatusDropMenu = false }) {
                            state.colorCheckList.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = "ID: ${it.id} - Name: ${it.name} - Value: ${it.value}") },
                                    onClick = {
                                        adminAddInventoryViewModel.onEvent(
                                            AdminAddInventoryEvent.ChangeColor(
                                                it
                                            )
                                        )
                                        selectedColor =
                                            "ID: ${it.id} - Name: ${it.name} - Value: ${it.value}"
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
                    onClick = { adminAddInventoryViewModel.onEvent(AdminAddInventoryEvent.Add) },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Add")
                }
            }
        }
    }
}