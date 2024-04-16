package com.triphuc22ad.shoesshop.util.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun OptionSwipeableContainer(
    name: String,
    active: Boolean = false,
    onSwipeDown: () -> Unit,
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .scrollable(
                            orientation = Orientation.Vertical,
                            state = rememberScrollableState { delta ->
                                if (delta > 40) {
                                    onSwipeDown()
                                    delta
                                } else {
                                    0f
                                }
                            }
                        )
                ) {
                    HorizontalDivider(
                        thickness = DividerDefaults.Thickness.plus(2.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .width(20.dp)
                    )
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                    HorizontalDivider()
                }
                content()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OptionSwipeableContainerPreview() {
    Dacs3shoesshopandroidTheme {
        var isVisible by remember { mutableStateOf(false) }
        OptionSwipeableContainer(name = "Test", active = true, onSwipeDown = {}) {
        }
    }
}
