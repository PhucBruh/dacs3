package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchBar(
    query: String,
    active: Boolean,
    onActiveChange: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = {},
        onSearch = {},
        active = active,
        onActiveChange = { onActiveChange() },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            if (active) {
                IconButton(onClick = { onClear() }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.List,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        },
        colors = SearchBarDefaults.colors(containerColor = if (active) Color.White else BgColor),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            SectionHeader(
                name = "Recent", actionName = "Clear All", onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(List(10) { "search history $it" }) {
                    SearchItem(name = it, onDelete = {}, onSelect = {})
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    name: String,
    onDelete: () -> Unit,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() }
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(28.dp)) {
            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onDelete() }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductSearchBarPreview() {
    Dacs3shoesshopandroidTheme {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val isActivated by remember { mutableStateOf(true) }
            val query by remember { mutableStateOf("") }
            ProductSearchBar(
                active = isActivated,
                query = query,
                onActiveChange = {},
                onClear = {}
            )
        }
    }
}