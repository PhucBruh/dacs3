package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.AppTheme


@Composable
fun UserBar(
    modifier: Modifier = Modifier,
    onWishListClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    name: String = "",
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "${getGreeting()} \uD83D\uDC4B",
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun getGreeting(): String {
    val currentHour = System.currentTimeMillis() % (1000 * 60 * 60 * 24) / (1000 * 60 * 60)

    return when (currentHour) {
        in 5..11 -> "Good morning"
        in 12..13 -> "Good noon"
        in 14..17 -> "Good afternoon"
        else -> "Good evening"
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserBarPreview() {
    AppTheme {
        Surface {
            UserBar(Modifier.padding(horizontal = 16.dp))
        }
    }
}