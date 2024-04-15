package com.triphuc22ad.shoesshop.presentation.product_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun TextCircleButton(
    text: String,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }
    val backgroundColor = if (isActivated) Color.Black else Color.White
    val textColor = if (isActivated) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(2.dp, Color.Black, CircleShape)
            .size(60.dp)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .clickable {
                isActivated = !isActivated
                onClick(isActivated)
            }
    ) {
        Text(text = text, color = textColor)
    }
}


@Composable
fun ColorCircleButton(
    color: Color,
    checkedColor: Color = Color.White,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(60.dp)
            .background(color, CircleShape)
            .clip(CircleShape)
            .clickable {
                isActivated = !isActivated
                onClick(isActivated)
            }
    ) {
        if (isActivated) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = checkedColor
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CircleButtonPreview() {
    Dacs3shoesshopandroidTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextCircleButton(text = "40", onClick = {})
            ColorCircleButton(color = Color.Cyan , onClick = {})
        }
    }
}