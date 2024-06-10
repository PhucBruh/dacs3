@file:OptIn(ExperimentalMaterial3Api::class)

package com.triphuc22ad.shoesshop.presentation.admin.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.components.OptionSwipeableContainer
import com.triphuc22ad.shoesshop.ui.theme.BgColor

data class MenuItem(
    val name: String,
    val unitPrice: Double,
    val imageResourceId: Int // Add this field for image resource ID
)

@Composable
fun ProductsScreen() {
    val menuItems = listOf(
        MenuItem("Item 1", 10.0, R.drawable.curry_6),
        MenuItem("Item 2", 15.0, R.drawable.jd7),
        MenuItem("Item 3", 20.0, R.drawable.curry_6),
        MenuItem("Item 4", 25.0, R.drawable.curry_6),
        MenuItem("Item 5", 30.0, R.drawable.curry_6),
        MenuItem("Item 6", 35.0, R.drawable.curry_6),
        MenuItem("Item 7", 10.0, R.drawable.curry_6),
        MenuItem("Item 8", 15.0, R.drawable.curry_6),
        MenuItem("Item 9", 20.0, R.drawable.curry_6),
        MenuItem("Item 10", 25.0, R.drawable.curry_6),
        MenuItem("Item 11", 30.0, R.drawable.curry_6),
        MenuItem("Item 12", 35.0, R.drawable.curry_6)
    )

    PaginatedSortableTable(menuItems)

    SmallExample(onClick = {})
}

@Composable
fun PaginatedSortableTable(menuItems: List<MenuItem>) {
    var searchText by remember { mutableStateOf("") }
    var sortedMenuItems by remember { mutableStateOf(menuItems) }
    var isAscending by remember { mutableStateOf(true) }
    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 10

    val totalPages = (sortedMenuItems.size + itemsPerPage - 1) / itemsPerPage

    fun sortItems() {
        sortedMenuItems = if (isAscending) {
            sortedMenuItems.sortedBy { it.unitPrice }
        } else {
            sortedMenuItems.sortedByDescending { it.unitPrice }
        }
    }

    LazyColumn {
        item {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Item Name", modifier = Modifier.weight(1f))
                Text(
                    " ${if (isAscending) "↑↓" else "↓↑"}",
                    modifier = Modifier
                        .weight(0.5f)
                        .clickable { isAscending = !isAscending; sortItems() },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text("Actions", modifier = Modifier.weight(0.5f))
            }

            val filteredItems = sortedMenuItems.filter {
                it.name.contains(searchText, ignoreCase = true)
            }

            val startIndex = currentPage * itemsPerPage
            val endIndex = (startIndex + itemsPerPage).coerceAtMost(filteredItems.size)
            for (i in startIndex until endIndex) {
                val item = filteredItems[i]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
                                .size(120.dp)
                                .background(BgColor, RoundedCornerShape(32.dp))
                        ) {
                            Image(
                                painter = painterResource(id = item.imageResourceId),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            item.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "$ ${item.unitPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Row {
                        IconButton(onClick = { /* Handle edit action */ }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit")
                        }

                        IconButton(onClick = { /* Handle delete action */ }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { if (currentPage > 0) currentPage-- },
                    enabled = currentPage > 0
                ) {
                    Text("Previous")
                }
                Text("Page ${currentPage + 1} of $totalPages")
                Button(
                    onClick = { if (currentPage < totalPages - 1) currentPage++ },
                    enabled = currentPage < totalPages - 1
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun SmallExample(onClick: () -> Unit) {
    var isOptionVisible by remember { mutableStateOf(false) }
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }

    if (isOptionVisible) {
        OptionSwipeableContainer(
            name = "add product",
            active = true,
            onSwipeDown = { isOptionVisible = false },
            firstActionName = "Reset",
            onFirstAction = { },
            secondActionName = "Apply",
            onSecondAction = { }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    TextField(
                        value = productName,
                        onValueChange = { productName = it },
                        label = { Text("Name") },
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(bottom = 4.dp)
                            .fillMaxWidth() // Adjust padding if needed
                    )
                }

                item {
                    TextField(
                        value = productPrice,
                        onValueChange = { productPrice = it },
                        label = { Text("Description") },
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(bottom = 4.dp)
                            .fillMaxWidth() // Adjust padding if needed
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = productType,
                            onValueChange = { productType = it },
                            label = { Text("Price") },
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .width(220.dp)
                        )

                        DropDown(
                            items = listOf("1", "2", "3", "4"),
                            lable = "brandId",
                            modifier = Modifier.weight(0.5f)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DropDown(
                            items = listOf("red", "blue", "white", "black"),
                            lable = "color",
                            modifier = Modifier.width(170.dp)
                        )

                        DropDown(
                            items = listOf("40", "41", "42", "43"),
                            lable = "size",
                            modifier = Modifier.width(170.dp)
                        )
                    }
                }

                item {
                    DisplayImageOnButtonClick()
                }

                item{
                    DisplayMultipleImages()
                }
            }

        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            SmallFloatingActionButton(
                onClick = { isOptionVisible = true },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    }
}



@Composable
fun DropDown(
    items: List<String>,
    lable: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            label = { Text(lable) },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = modifier // Apply modifier
                .menuAnchor()
                .clip(RoundedCornerShape(20.dp))
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedText = item
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DisplayImageOnButtonClick() {
    val imageUrl = remember { mutableStateOf("") }
    val showTextField = remember { mutableStateOf(false) }
    val showImage = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showTextField.value) {
            TextField(
                value = imageUrl.value,
                onValueChange = { newValue ->
                    imageUrl.value = newValue
                },
                label = { Text("Enter Image URL") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Button(
                onClick = {
                    // Load image from URL
                    showImage.value = true
                    showTextField.value = false // Hide text field after showing the image
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Show Image")
            }
        } else {
            Button(
                onClick = {
                    // Show the text field to enter URL
                    showTextField.value = true
                    showImage.value = false // Reset image display state
                    imageUrl.value = "" // Clear previous URL
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Enter Image URL")
            }
        }

        if (showImage.value && imageUrl.value.isNotBlank()) {
            // Display image if URL is not blank
            Box {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl.value,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(200.dp)
                )
                IconButton(
                    onClick = {
                        // Hide image and clear URL when X button is clicked
                        showImage.value = false
                        imageUrl.value = ""
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }
            }
        } else if (showImage.value && imageUrl.value.isBlank()) {
            Text("Please enter a valid URL", color = Color.Red)
        }
    }
}

@Composable
fun DisplayMultipleImages() {
    var imageUrls by remember { mutableStateOf(listOf<String>()) }
    var newImageUrl by remember { mutableStateOf("") }
    var showTextField by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = 50.dp)
    ) {
        if (showTextField) {
            TextField(
                value = newImageUrl,
                onValueChange = { newImageUrl = it },
                label = { Text("Enter Image URL") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Button(
                onClick = {
                    if (newImageUrl.isNotBlank()) {
                        // Add new URL to the list and reset text field
                        imageUrls = imageUrls + newImageUrl
                        newImageUrl = ""
                        showTextField = false
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Add")
            }
        } else {
            Button(
                onClick = { showTextField = true },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Enter Image URL")
            }
        }

        // Display images
        LazyRow(
            content = {
                items(imageUrls) { imageUrl ->
                    var isImageVisible by remember { mutableStateOf(true) }

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray)
                    ) {
                        Image(
                            painter = rememberImagePainter(imageUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        if (isImageVisible) {
                            IconButton(
                                onClick = { isImageVisible = false },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductsScreen()
}
