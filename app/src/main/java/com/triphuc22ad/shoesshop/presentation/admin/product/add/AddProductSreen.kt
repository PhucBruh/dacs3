package com.triphuc22ad.shoesshop.presentation.admin.product.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.FilterOptionCanClose
import com.triphuc22ad.shoesshop.presentation.components.ImagePreview
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.presentation.util.parseColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AdminAddProductScreen(
    manageProductViewModel: AddProductViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state by manageProductViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Add new product", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            item {
                TextField(
                    value = state.productToAdd.name,
                    onValueChange = {
                        manageProductViewModel.onEvent(
                            AddProductEvent.ChangeName(it)
                        )
                    },
                    singleLine = true,
                    label = { Text("Name") },
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.productToAdd.description,
                    onValueChange = {
                        manageProductViewModel.onEvent(
                            AddProductEvent.ChangeDescription(
                                it
                            )
                        )
                    },
                    label = { Text("Description") },
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                var brandId by remember { mutableStateOf("") }
                TextField(
                    value = brandId,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            brandId = newValue
                            manageProductViewModel.onEvent(
                                AddProductEvent.ChangeBrandId(
                                    newValue.toIntOrNull() ?: 0
                                )
                            )
                        }
                    },
                    label = { Text("Bran Id") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                var price by remember { mutableStateOf("") }
                TextField(
                    value = price,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            price = newValue
                            manageProductViewModel.onEvent(
                                AddProductEvent.ChangePrice(
                                    newValue.toDoubleOrNull() ?: 0.0
                                )
                            )
                        }
                    },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.productToAdd.mainImg,
                        onValueChange = {
                            manageProductViewModel.onEvent(
                                AddProductEvent.ChangeMainImg(it)
                            )
                        },
                        label = { Text("Main img") },
                        modifier = Modifier
                            //
                            .weight(0.7f)
                            .padding(end = 8.dp)
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        onClick = { manageProductViewModel.onEvent(AddProductEvent.CheckImg) },
                        modifier = Modifier
                    ) {
                        Text(text = "Check")
                    }
                }
            }

            if (state.productToAddMainImgPreview != 0L) {
                item {
                    ImagePreview(
                        model = state.productToAdd.mainImg,
                        description = state.productToAddMainImgPreview.toString(),
                        onClick = { manageProductViewModel.onEvent(AddProductEvent.DeleteCheckImg) })
                }
            }

            // size adding section
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var sizeToEdit by remember { mutableStateOf("") }

                    TextField(
                        value = sizeToEdit,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                sizeToEdit = newValue
                            }
                        },
                        label = { Text("Size") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .weight(0.7f)
                            //
                            .padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            manageProductViewModel.onEvent(
                                AddProductEvent.AddSize(
                                    sizeToEdit.toIntOrNull() ?: 0
                                )
                            )
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Black
                        ),
                        modifier = Modifier.size(52.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }
                }
            }

            if (state.productToAdd.sizes.isNotEmpty()) {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        state.productToAdd.sizes.forEach {
                            FilterOptionCanClose(onClick = {
                                manageProductViewModel.onEvent(
                                    AddProductEvent.DeleteSize(it)
                                )
                            }) {
                                Text("$it")
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var colorNameToEdit by remember { mutableStateOf("") }
                    TextField(
                        value = colorNameToEdit,
                        onValueChange = { colorNameToEdit = it },
                        label = { Text("Color name") },
                        modifier = Modifier
                            //
                            .weight(0.4f)
                            .padding(end = 8.dp)
                    )

                    var colorValueToEdit by remember { mutableStateOf("") }
                    TextField(
                        value = colorValueToEdit,
                        onValueChange = { colorValueToEdit = it },
                        label = { Text("Color value") },
                        modifier = Modifier
                            //
                            .weight(0.4f)
                            .padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            manageProductViewModel.onEvent(
                                AddProductEvent.AddColor(
                                    colorNameToEdit,
                                    colorValueToEdit
                                )
                            )
                            colorNameToEdit = ""
                            colorValueToEdit = ""
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Black
                        ),
                        modifier = Modifier.size(52.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }
                }
            }

            if (state.productToAdd.colors.isNotEmpty()) {
                item {
                    FlowRow() {
                        state.productToAdd.colors.forEach {
                            FilterOptionCanClose(onClick = {
                                manageProductViewModel.onEvent(
                                    AddProductEvent.DeleteColor(
                                        name = it.name,
                                        value = it.value
                                    )
                                )
                            }) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(
                                            parseColor(it.value),
                                            CircleShape
                                        )
                                )
                                Text(
                                    text = it.name,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var imgToEdit by remember { mutableStateOf("") }

                    TextField(
                        value = imgToEdit,
                        onValueChange = { imgToEdit = it },
                        label = { Text("Sub image") },
                        modifier = Modifier
                            .weight(0.7f)
                            //
                            .padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            manageProductViewModel.onEvent(
                                AddProductEvent.AddImg(
                                    imgToEdit
                                )
                            )
                            imgToEdit = ""
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Black
                        ),
                        modifier = Modifier.size(52.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }
                }
            }

            if (state.productToAdd.imgs.isNotEmpty()) {
                item {
                    LazyRow {
                        items(state.productToAdd.imgs) {
                            ImagePreview(
                                model = it,
                                description = "",
                                onClick = {
                                    manageProductViewModel.onEvent(
                                        AddProductEvent.DeleteImg(
                                            it
                                        )
                                    )
                                })
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

@Composable
@Preview
fun AddProductScreenPreview() {
    AppTheme {
        Surface {
            AdminAddProductScreen()
        }
    }
}