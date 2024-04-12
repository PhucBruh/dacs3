package com.triphuc22ad.shoesshop.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchBar() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = true,
        onActiveChange = {},
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Recent")
                Text(text = "Clear all")
            }
            HorizontalDivider()
            LazyColumn {
                items(List(10) { "search $it" }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductSearchBarPreview() {
    ProductSearchBar()
}