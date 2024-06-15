package com.triphuc22ad.shoesshop.presentation.admin.dashboard

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import kotlin.math.round

enum class BarType { CIRCULAR_TYPE, TOP_CURVED }

@Composable
fun BarGraph(
    graphBarData: List<Float>,
    xAxisScaleData: List<Int>,
    barData_: List<Int>,
    height: Dp,
    roundType: BarType,
    barWidth: Dp,
    barColor: Color,
    barArrangement: Arrangement.Horizontal
) {
    val barData = barData_ + 0
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp
    val xAxisScaleHeight = 40.dp
    val yAxisScaleSpacing = 100f
    val yAxisTextWidth = 100.dp

    val barShape = when (roundType) {
        BarType.CIRCULAR_TYPE -> CircleShape
        BarType.TOP_CURVED -> RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
    }

    val density = LocalDensity.current
    val yCoordinates = mutableListOf<Float>()
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val lineHeightXAxis = 10.dp
    val horizontalLineHeight = 5.dp

    Column {
        Row { Text(text = "Completed Order", fontSize = 14.sp, modifier = Modifier.padding(bottom = 12.dp)) }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart) {
            Column(
                modifier = Modifier
                    .padding(top = xAxisScaleHeight, end = 3.dp)
                    .height(height),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize()
                ) {
                    val yAxisScaleText = barData.max() / 3f
                    val textPaint = Paint().apply {
                        color = Color.Black.hashCode()
                        textAlign = Paint.Align.CENTER
                        textSize = density.run { 12.sp.toPx() }
                    }
                    (0..3).forEach { i ->
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                round(barData.min() + yAxisScaleText * i).toInt().toString(),
                                30f,
                                size.height - yAxisScaleSpacing - i * size.height / 3f,
                                textPaint
                            )
                        }
                        yCoordinates.add(size.height - yAxisScaleSpacing - i * size.height / 3f)
                    }
                    (1..3).forEach {
                        drawLine(
                            start = Offset(x = yAxisScaleSpacing + 30f, y = yCoordinates[it]),
                            end = Offset(x = size.width, y = yCoordinates[it]),
                            color = Color.Gray,
                            strokeWidth = 5f,
                            pathEffect = pathEffect
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(width - yAxisTextWidth)
                    .height(height + xAxisScaleHeight),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.width(width - yAxisTextWidth),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = barArrangement
                ) {
                    graphBarData.forEachIndexed { index, value ->
                        var animationTriggered by remember { mutableStateOf(false) }
                        val graphBarHeight by animateFloatAsState(
                            targetValue = if (animationTriggered) value else 0f,
                            animationSpec = tween(durationMillis = 1000)
                        )
                        LaunchedEffect(key1 = true) { animationTriggered = true }

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .clip(barShape)
                                    .width(barWidth)
                                    .height(height - 10.dp)
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(barShape)
                                        .fillMaxWidth()
                                        .fillMaxHeight(graphBarHeight)
                                        .background(barColor)
                                )
                            }
                            Column(
                                modifier = Modifier.height(xAxisScaleHeight),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                bottomStart = 2.dp,
                                                bottomEnd = 2.dp
                                            )
                                        )
                                        .width(horizontalLineHeight)
                                        .height(lineHeightXAxis)
                                        .background(color = Color.Gray)
                                )
                                Text(
                                    modifier = Modifier.padding(bottom = 3.dp),
                                    text = xAxisScaleData[index].toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = xAxisScaleHeight + 3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .fillMaxWidth()
                            .height(horizontalLineHeight)
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AnalysisPreview() {
    AppTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.Center,

                ) {
                val dataList = listOf(30, 8, 12, 23)
                val floatValue = dataList.map { it.toFloat() / dataList.max().toFloat() }
                val datesList = listOf(1, 2, 3, 4)

                BarGraph(
                    graphBarData = floatValue,
                    xAxisScaleData = datesList,
                    barData_ = dataList,
                    height = 300.dp,
                    roundType = BarType.TOP_CURVED,
                    barWidth = 20.dp,
                    barColor = Color(0xFF6fe349),
                    barArrangement = Arrangement.SpaceEvenly
                )
            }
        }
    }
}
