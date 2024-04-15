package com.triphuc22ad.shoesshop.presentation.product_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SizeComponent(
    text: String,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }
    val backgroundColor = if (isActivated) Color.White else Color.Black
    val textColor = if (isActivated) Color.Black else Color.White

    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                isActivated = !isActivated
                onClick(isActivated)
            }
            .border(BorderStroke(width = 2.dp, color = Color.Gray))
            .background(backgroundColor)
            .padding(12.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun review(){
    SizeComponent(text = "40", onClick = {})
}