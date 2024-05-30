package com.triphuc22ad.shoesshop.presentation.admin.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        MenuItem("Item 2", 15.0, R.drawable.curry_6),
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
        ) }

        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
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
                // Thêm cột cho các nút "Edit" và "Delete"
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
                Row(modifier = Modifier
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
                                modifier = Modifier
                                    .size(100.dp)
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
                            "$ " + item.unitPrice.toString(),
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
            Button(onClick = { if (currentPage > 0) currentPage-- }, enabled = currentPage > 0) {
                Text("Previous")
            }
            Text("Page ${currentPage + 1} of $totalPages")
            Button(onClick = { if (currentPage < totalPages - 1) currentPage++ }, enabled = currentPage < totalPages - 1) {
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
    var uploadedImageUri by remember { mutableStateOf<Uri?>(null) }

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
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                // TextField for product name
                TextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Product Name") },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                )


                TextField(
                    value = productPrice,
                    onValueChange = { productPrice = it },
                    label = { Text("Price") },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                )

                TextField(
                    value = productType,
                    onValueChange = { productType = it },
                    label = { Text("Product Type") },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                )

                
                Button(onClick = {
                    onClick.invoke()
                }) {
                    Text("Upload Image")
                }

                uploadedImageUri?.let { uri ->
                    Image(
                        painter = rememberImagePainter(uri),
                        contentDescription = "Uploaded Image",
                        modifier = Modifier.size(100.dp)
                    )
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





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductsScreen()
}
