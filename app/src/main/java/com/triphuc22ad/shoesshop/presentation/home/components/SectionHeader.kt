package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun SectionHeader(
    name: String,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = name)
        Text(text = "See All", Modifier.clickable { onSeeAllClick() })
    }
}

@Preview
@Composable
fun SectionHeaderPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            SectionHeader(
                "Special Offer",
                {},
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}