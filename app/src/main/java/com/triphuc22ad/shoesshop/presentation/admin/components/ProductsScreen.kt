import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor

data class MenuItem(val name: String, val unitPrice: Double)

@Composable
fun ProductsScreen() {
    val menuItems = listOf(
        MenuItem("Item 1", 10.0),
        MenuItem("Item 2", 15.0),
        MenuItem("Item 3", 20.0),
        MenuItem("Item 4", 25.0),
        MenuItem("Item 5", 30.0),
        MenuItem("Item 6", 35.0),
        MenuItem("Item 7", 10.0),
        MenuItem("Item 8", 15.0),
        MenuItem("Item 9", 20.0),
        MenuItem("Item 10", 25.0),
        MenuItem("Item 11", 30.0),
        MenuItem("Item 12", 35.0)
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

    Column {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text("Item Name", modifier = Modifier.weight(1f))
            Text(
                " ${if (isAscending) "↑↓" else "↓↑"}",
                modifier = Modifier
                    .weight(1f)
                    .clickable { isAscending = !isAscending; sortItems() },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
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
                .padding(8.dp)
            ) {
                Text(item.name, modifier = Modifier.weight(1f))
                Text(item.unitPrice.toString(), modifier = Modifier.weight(1f))
            }
        }

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

@Composable
fun SmallExample(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        SmallFloatingActionButton(
            onClick = { onClick() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductsScreen()
}
