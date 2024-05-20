package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun TopTitleBar(
    name: String,
    leftIconAction: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    onLeftAction: () -> Unit = {},
    rightIconAction: ImageVector? = null,
    onRightAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leftIconAction != null) {
                IconButton(onClick = { onLeftAction() }, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = leftIconAction,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        if (rightIconAction != null) {
            IconButton(onClick = { onRightAction() }, modifier = Modifier.size(28.dp)) {
                Icon(
                    imageVector = rightIconAction,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BackBarPreview() {
    AppTheme {
        Surface {
            Column {
                TopTitleBar(
                    name = "My Wishlist",
                )
            }
        }
    }
}