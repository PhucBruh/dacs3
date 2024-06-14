package com.triphuc22ad.shoesshop.presentation.order

import TabScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun MyOrderScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    navigateToOrderInfo: (Int) -> Unit = {}
) {
    val state = orderViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.fetchData()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
        ) {
            TopTitleBar(
                leftIconAction = null,
                name = "My Orders",
            )
        }

        TabScreen(
            inCompletedOrders = state.value.inCompletedOrders,
            completedOrders = state.value.completedOrders,
            navigateToOrderInfo = navigateToOrderInfo
        )
    }

}

@Preview
@Composable
fun ProductScreenPreview() {
    AppTheme {
        Surface {
            MyOrderScreen()
        }
    }
}