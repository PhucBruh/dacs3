package com.triphuc22ad.shoesshop.presentation.admin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.Analysis
import com.triphuc22ad.shoesshop.data.service.AnalysisService
import com.triphuc22ad.shoesshop.data.service.WeeklyReport
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.util.formatPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDashBoardViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val analysisService: AnalysisService
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    fun fetchAnalysis() {
        viewModelScope.launch {
            val response = analysisService.getAnalysis()
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        val data = result.data
                        _state.value = DashboardState(
                            analysis = data!!
                        )
                    }
                }
            } else {
                appStateRepository.updateNotify("Failed to fetch analysis")
            }
        }
    }

    fun fetchMonthlyReport(month: Int, year: Int) {
        if (month in 1..12 && year > 0) {
            viewModelScope.launch {
                val response = analysisService.getMonthlyReport(
                    month = month,
                    year = year
                )
                if (response.isSuccessful) {
                    val result = response.body()
                    _state.value = _state.value.copy(
                        monthly = result ?: emptyList(),
                        month = month,
                        year = year
                    )
                } else {
                    appStateRepository.updateNotify("Failed to fetch monthly report")
                }
            }
        } else {
            appStateRepository.updateNotify("Invalid month or year");
        }
    }
}

data class DashboardState(
    val analysis: Analysis = Analysis(),
    val monthly: List<WeeklyReport> = emptyList(),
    val month: Int = 0,
    val year: Int = 0
)

@Composable
fun AdminDashBoardScreen(
    adminDashBoardViewModel: AdminDashBoardViewModel = hiltViewModel()
) {
    val state by adminDashBoardViewModel.state.collectAsState()
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        adminDashBoardViewModel.fetchAnalysis()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HeaderItem(
                        category = "EARNING (MONTHLY)",
                        value = formatPrice(state.analysis.monthly),
                        color = Color.Blue,
                        modifier = Modifier.weight(0.48f)
                    )
                    Spacer(modifier = Modifier.weight(0.04f))
                    HeaderItem(
                        category = "EARNING (ANNUAL)",
                        value = formatPrice(state.analysis.yearly),
                        color = Color.Red,
                        modifier = Modifier.weight(0.48f)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {

                    HeaderItem(
                        category = "INCOMPLETE ORDER",
                        value = state.analysis.incompleteOrder.toString(),
                        color = Color.Blue,
                        modifier = Modifier.weight(0.48f)
                    )
                    Spacer(modifier = Modifier.weight(0.04f))
                    HeaderItem(
                        category = "COMPLETED ORDER",
                        value = state.analysis.completedOrder.toString(),
                        color = Color.Red,
                        modifier = Modifier.weight(0.48f)
                    )
                }
            }
        }


        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Analysis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = month,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            month = newValue
                        }
                    },
                    singleLine = true,
                    label = { Text("Month") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(0.4f)
                )

                Spacer(modifier = Modifier.weight(0.05f))

                TextField(
                    value = year,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            year = newValue
                        }
                    },
                    singleLine = true,
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(0.4f)
                )
            }
        }

        item {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    adminDashBoardViewModel.fetchMonthlyReport(
                        month = month.toIntOrNull() ?: 0,
                        year = year.toIntOrNull() ?: 0
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "View Analysis")
            }
        }

        if (state.monthly.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Result for ${state.month}-${state.year}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                ) {
                    val dataList = state.monthly.map { it.completedOrder }
                    val floatValue = dataList.map { it.toFloat() / dataList.max().toFloat() }
                    val datesList = List(state.monthly.size) { it }

                    Box(contentAlignment = Alignment.BottomStart) {
                        BarGraph(
                            graphBarData = floatValue,
                            xAxisScaleData = datesList,
                            barData_ = dataList,
                            height = 300.dp,
                            roundType = BarType.TOP_CURVED,
                            barWidth = 30.dp,
                            barColor = Color(0xFF6fe349),
                            barArrangement = Arrangement.SpaceEvenly
                        )
                        Text(text = "Week", modifier = Modifier.padding(bottom = 18.dp))
                    }
                    Text(text = "Total earn: " + formatPrice(state.monthly.sumOf { it.earn }))
                }
            }

            items(state.monthly) {
                LazyRow() {
                    item {
                        Text(
                            text = "Start: ${it.weekStart} -> End: ${it.weekEnd} -> Earn: ${
                                formatPrice(
                                    it.earn
                                )
                            }"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(
    category: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = category,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Text(
                text = value,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    AdminDashBoardScreen()
}