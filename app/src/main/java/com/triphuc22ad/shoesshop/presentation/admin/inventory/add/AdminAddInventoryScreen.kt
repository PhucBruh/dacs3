package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
            name = "Add new brand", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.productId.toString(),
                        onValueChange = {

                        },
                        label = { Text("Img") },
                        modifier = Modifier
                            //
                            .weight(0.7f)
                            .padding(end = 8.dp)
                    )
                    Button(onClick = {}) {
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
                        TextField(
                            value = state.selectedSizeId.toString(),
                            onValueChange = {},
                            label = { Text("Status") },
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
                            state.sizeCheckList.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = it.size.toString()) },
                                    onClick = {
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
                        TextField(
                            value = state.selectedColorId.toString(),
                            onValueChange = {},
                            label = { Text("Status") },
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
                                    text = { Text(text = it.name) },
                                    onClick = {
                                        isExpandedStatusDropMenu = false
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}