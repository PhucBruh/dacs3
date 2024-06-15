package com.triphuc22ad.shoesshop.presentation.admin.product.edit

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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.FilterOptionCanClose
import com.triphuc22ad.shoesshop.presentation.components.ImagePreview
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.presentation.util.parseColor

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navigateBack: () -> Unit = {},
    editProductViewModel: EditProductViewModel = hiltViewModel(),
) {
    val state by editProductViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        editProductViewModel.fetchData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Edit product", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {

            // id section
            item {
                TextField(
                    value = state.productDetail.id.toString(),
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Id") },
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.productDetail.name,
                    onValueChange = {
                        editProductViewModel.onEvent(
                            EditProductEvent.ChangeName(it)
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
                    value = state.productDetail.description,
                    onValueChange = {
                        editProductViewModel.onEvent(
                            EditProductEvent.ChangeDescription(
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
                var brandId by remember { mutableStateOf(state.productDetail.brand.id.toString()) }
                TextField(
                    value = brandId,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            brandId = newValue
                            editProductViewModel.onEvent(
                                EditProductEvent.ChangeBrandId(
                                    newValue.toIntOrNull() ?: 0
                                )
                            )
                        }
                    },
                    label = { Text("Bran Id") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                var price by remember { mutableStateOf("${state.productDetail.price.toInt()}") }
                TextField(
                    value = price,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            price = newValue
                            editProductViewModel.onEvent(
                                EditProductEvent.ChangePrice(
                                    newValue.toDoubleOrNull() ?: 0.0
                                )
                            )
                        }
                    },
                    label = { Text("Price") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                var isExpandedStatusDropMenu by remember { mutableStateOf(false) }
                val statusList = listOf("ACTIVE", "INACTIVE", "OUT_OF_STOCK")
                Box {
                    ExposedDropdownMenuBox(
                        expanded = isExpandedStatusDropMenu,
                        onExpandedChange = { isExpandedStatusDropMenu = it }) {
                        TextField(
                            value = state.productDetail.status,
                            onValueChange = {},
                            label = { Text("Status") },
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedStatusDropMenu) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = isExpandedStatusDropMenu,
                            onDismissRequest = { isExpandedStatusDropMenu = false }) {
                            statusList.forEach {
                                DropdownMenuItem(
                                    text = { Text(text = it) },
                                    onClick = {
                                        editProductViewModel.editStatus(it)
                                        isExpandedStatusDropMenu = false
                                    })
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
                    TextField(
                        value = state.productDetail.mainImg,
                        onValueChange = {
                            editProductViewModel.onEvent(
                                EditProductEvent.ChangeMainImg(it)
                            )
                        },
                        singleLine = true,
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
                        onClick = { editProductViewModel.onEvent(EditProductEvent.CheckImg) },
                        modifier = Modifier
                    ) {
                        Text(text = "Check")
                    }
                }
            }

            item {
                ImagePreview(
                    model = state.productDetail.mainImg,
                    description = state.productDetailMainImgPreview.toString(),
                    onClick = { editProductViewModel.onEvent(EditProductEvent.DeleteCheckImg) })
            }


            item {
                Column {
                    Text(text = "Size", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        state.productDetail.sizes.forEach {
                            FilterOptionCanClose(onClick = {
                                editProductViewModel.onEvent(
                                    EditProductEvent.DeleteEditSize(it.size)
                                )
                            }) {
                                Text("${it.size}")
                            }
                        }
                    }
                }
            }

            item {
                Column {
                    Text(text = "Color", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        state.productDetail.colors.forEach {
                            FilterOptionCanClose(onClick = {
                                editProductViewModel.onEvent(
                                    EditProductEvent.DeleteEditColor(
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
                Column {
                    Text(text = "Image", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(state.productDetail.imgs) {
                            ImagePreview(
                                model = it.url,
                                description = "",
                                onClick = {
                                    editProductViewModel.onEvent(
                                        EditProductEvent.DeleteEditImg(
                                            it.url
                                        )
                                    )
                                })
                        }
                    }
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
                        singleLine = true,
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
                            editProductViewModel.onEvent(
                                EditProductEvent.AddEditSize(
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
            if (state.editSizes.isNotEmpty()) {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        state.editSizes.forEach {
                            FilterOptionCanClose(onClick = {
                                editProductViewModel.onEvent(
                                    EditProductEvent.DeleteEditSize(it)
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
                        singleLine = true,
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
                            editProductViewModel.onEvent(
                                EditProductEvent.AddEditColor(
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

            if (state.editColors.isNotEmpty()) {
                item {
                    FlowRow(
                    ) {
                        state.editColors.forEach {
                            FilterOptionCanClose(onClick = {
                                editProductViewModel.onEvent(
                                    EditProductEvent.DeleteEditColor(
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
                        label = { Text("Size") },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.7f)
                            //
                            .padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            editProductViewModel.onEvent(
                                EditProductEvent.AddEditImg(
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

            if (state.editImgs.isNotEmpty()) {
                item {
                    LazyRow {
                        items(state.editImgs) {
                            ImagePreview(
                                model = it,
                                description = "",
                                onClick = {
                                    editProductViewModel.onEvent(
                                        EditProductEvent.DeleteEditImg(
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
                    onClick = { editProductViewModel.onEvent(EditProductEvent.CheckImg) },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Update")
                }
            }
        }
    }

}