package com.triphuc22ad.shoesshop.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun OptionSwipeableContainer(
    active: Boolean = false,
    name: String,
    onSwipeDown: () -> Unit,
    firstActionName: String,
    onFirstAction: () -> Unit,
    secondActionName: String,
    onSecondAction: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    AnimatedVisibility(
        visible = active,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onSwipeDown() } // Close on background click
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .border(
                        border = BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            ) {

                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                content()

                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                Row(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BgColor,
                            contentColor = Color.Black
                        ),
                        onClick = { onFirstAction() }, modifier = Modifier
                            .weight(0.8f)
                            .padding(end = 12.dp)
                    ) {
                        Text(text = firstActionName)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        onClick = { onSecondAction() }, modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 12.dp)
                    ) {
                        Text(text = secondActionName)
                    }
                    IconButton(
                        onClick = { onSwipeDown() }, // Close button
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun OptionSwipeableContainerPreview() {
    AppTheme {
        var isVisible by remember { mutableStateOf(false) }
        OptionSwipeableContainer(
            name = "Test",
            active = true,
            onSwipeDown = {},
            firstActionName = "Reset",
            onFirstAction = {},
            secondActionName = "Apply",
            onSecondAction = {}
        ) {
        }
    }
}
