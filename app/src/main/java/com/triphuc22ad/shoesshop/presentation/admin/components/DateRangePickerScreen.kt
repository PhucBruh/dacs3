package com.triphuc22ad.shoesshop.presentation.admin.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerSample() {
    val state = rememberDateRangePickerState()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        DateRangePicker(state = state, modifier = Modifier.weight(1f), title = {},)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDate(){
    DateRangePickerSample()
}