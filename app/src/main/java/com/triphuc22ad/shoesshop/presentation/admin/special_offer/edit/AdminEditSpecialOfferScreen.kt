package com.triphuc22ad.shoesshop.presentation.admin.special_offer.edit

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
import androidx.compose.runtime.LaunchedEffect
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
fun AdminEditSpecialOfferScreen(
    adminEditSpecialOfferViewModel: AdminEditSpecialOfferViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state by adminEditSpecialOfferViewModel.state.collectAsState()
    var fetched by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        adminEditSpecialOfferViewModel.fetchData()
        fetched = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Edit special offer", modifier = Modifier.padding(top = 16.dp),
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
                        value = state.id.toString(),
                        onValueChange = {},
                        enabled = false,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("ID") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.productId.toString(),
                        onValueChange = {},
                        enabled = false,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("Product Id") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            item {
                TextField(
                    value = state.name,
                    onValueChange = {
                    },
                    enabled = false,
                    singleLine = true,
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            item {
                TextField(
                    value = state.name,
                    onValueChange = {

                    },
                    enabled = false,
                    singleLine = true,
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            item {
                var valueToEdit by remember { mutableStateOf(state.value.toInt().toString()) }
                LaunchedEffect(fetched) {
                    valueToEdit = state.value.toInt().toString()
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.value.toInt().toString(),
                        onValueChange = {},
                        enabled = false,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("Value") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }


            item {
                var isExpandedActiveMenu by remember { mutableStateOf(false) }
                val listOption = listOf(true, false)
                Box {
                    ExposedDropdownMenuBox(
                        expanded = isExpandedActiveMenu,
                        onExpandedChange = { isExpandedActiveMenu = it }) {
                        TextField(
                            value = state.active.toString(),
                            onValueChange = {},
                            label = { Text("Active") },
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedActiveMenu) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth() // Adjust padding if needed
                        )
                        ExposedDropdownMenu(
                            expanded = isExpandedActiveMenu,
                            onDismissRequest = { isExpandedActiveMenu = false }) {
                            listOption.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = it.toString()) },
                                    onClick = {
                                        isExpandedActiveMenu = false
                                        adminEditSpecialOfferViewModel.changeActive(it)
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
                    onClick = { adminEditSpecialOfferViewModel.update() },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Update")
                }
            }
        }
    }
}