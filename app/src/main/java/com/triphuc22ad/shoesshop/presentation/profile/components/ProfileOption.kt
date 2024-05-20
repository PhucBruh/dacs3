package com.triphuc22ad.shoesshop.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun ProfileOption(
    name: String,
    leftIcon: ImageVector,
    onClick: () -> Unit,
    description: String? = null,
    color: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    ProfileOptionContainer(
        name = name,
        icon = leftIcon,
        onClick = { onClick() },
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (description != null) {
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = color
                    )
                }
            }

            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.size(40.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    tint = color,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileOptionContainer(
    name: String,
    icon: ImageVector,
    color: Color = Color.Black,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(40.dp)) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = color
            )
        }
        content()
    }
}

@Preview
@Composable
fun ProfileOptionPreview() {
    AppTheme {
        Surface {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                ProfileOption(
                    name = "EditProile",
                    leftIcon = Icons.Outlined.Person,
                    description = "English(US)",
                    onClick = { /*TODO*/ },
                )
                ProfileOptionContainer(
                    name = "Dark Mode",
                    icon = Icons.Outlined.RemoveRedEye,
                    onClick = { /*TODO*/ }) {
                    Switch(checked = false, onCheckedChange = {})
                }
            }
        }
    }
}