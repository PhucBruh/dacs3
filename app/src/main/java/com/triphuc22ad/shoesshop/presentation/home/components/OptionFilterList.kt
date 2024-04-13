package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme


@Composable
fun OptionFilterList(
    modifier: Modifier = Modifier
) {
    val listFilter = List(10) { "Filter $it" }
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(listFilter) {
            OptionFilter(text = it, onClick = {}, modifier = Modifier.padding(end = 8.dp))
        }
    }
}

@Composable
fun OptionFilter(
    text: String,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }
    val backgroundColor = if (isActivated) Color.Black else Color.White
    val textColor = if (isActivated) Color.White else Color.Black

    OutlinedButton(
        onClick = {
            isActivated = !isActivated
            onClick(isActivated)
        },
        border = BorderStroke(width = 2.dp, color = Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun OptionFilterListPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            OptionFilterList()
        }
    }
}