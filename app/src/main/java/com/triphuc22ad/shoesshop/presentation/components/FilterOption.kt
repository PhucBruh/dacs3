package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun FilterOption(
    text: String,
    active: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (active) Color.Black else Color.White
    val textColor = if (active) Color.White else Color.Black

    OutlinedButton(
        onClick = { onClick() },
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

@Composable
fun FilterOptionCanClose(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .border(2.dp, Color.Black, RoundedCornerShape(24.dp))
            .padding(8.dp)
    ) {
        content()
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .border(2.dp, Color.Black, CircleShape)
                .size(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "")
        }
    }
}

@Composable
fun ImagePreview(
    model: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier
            .border(2.dp, Color.Black, RoundedCornerShape(24.dp))
            .padding(8.dp)
            .size(150.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(model = model, contentDescription = description)
        }
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .border(2.dp, Color.Black, CircleShape)
                .size(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = description)
        }
    }
}

@Preview
@Composable
fun OptionFilterList() {
    AppTheme {
        Surface {
            FilterOptionCanClose(onClick = { /*TODO*/ }) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            Color(android.graphics.Color.parseColor("#77eb34")),
                            CircleShape
                        )
                )
                Text(text = "red", fontWeight = FontWeight.Light, fontSize = 16.sp)
            }
        }
    }
}