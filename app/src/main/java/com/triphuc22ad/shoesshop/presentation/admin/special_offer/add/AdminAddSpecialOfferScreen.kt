package com.triphuc22ad.shoesshop.presentation.admin.special_offer.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun AdminAddSpecialOfferScreen(
    adminAddSpecialOfferViewModel: AdminAddSpecialOfferViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state by adminAddSpecialOfferViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Add new special offer", modifier = Modifier.padding(top = 16.dp),
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
                                adminAddSpecialOfferViewModel.changeProductId(
                                    newValue.toIntOrNull() ?: 0
                                )
                            }
                        },
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
                        adminAddSpecialOfferViewModel.changeName(it)
                    },
                    singleLine = true,
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            item {
                TextField(
                    value = state.description,
                    onValueChange = {
                        adminAddSpecialOfferViewModel.changeDescription(it)
                    },
                    singleLine = true,
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            item {
                var valueToEdit by remember { mutableStateOf("") }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = valueToEdit,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                valueToEdit = newValue
                                adminAddSpecialOfferViewModel.changeValue(
                                    newValue.toDoubleOrNull() ?: 0.0
                                )
                            }
                        },
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
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Add")
                }
            }
        }
    }
}