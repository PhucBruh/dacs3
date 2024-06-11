package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun QuantityButton(
    quantity: Int = 0,
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
    size: Dp = 32.dp,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(BgColor, RoundedCornerShape(32.dp))
            .height(size)
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .clickable { onDecrease() }) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                modifier = Modifier.size(size / 2)
            )
        }

        Text(
            text = quantity.toString(),
            fontSize = (size.value / 2).sp,
            textAlign = TextAlign.Center
        )
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .clickable { onIncrease() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(size / 2)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun QuantityButtonPreview() {
    AppTheme {
        Surface {
            QuantityButton(size = 32.dp)
        }
    }
}
