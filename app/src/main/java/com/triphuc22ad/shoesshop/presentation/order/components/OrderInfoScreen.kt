package com.triphuc22ad.shoesshop.presentation.order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.UserService
import com.triphuc22ad.shoesshop.domain.model.OrderDetail
import com.triphuc22ad.shoesshop.domain.model.OrderUserInfo
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderInfoViewModel @Inject constructor(
    private val userService: UserService,
    private val appStateRepository: AppStateRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId: Int =
        savedStateHandle["orderId"] ?: 0

    fun fetchData() {
        viewModelScope.launch {
            val response = userService.getMyOrderInfoById(orderId);
            if (response.isSuccessful) {
                val orderDetail = response.body()!!.data

                if (orderDetail != null) {
                    _state.value = orderDetail
                }
            } else {
                appStateRepository.updateNotify("order not found")
            }
        }
    }

    private val _state = MutableStateFlow(
        OrderDetail(
            id = 0,
            user = OrderUserInfo(name = "", phone = ""),
            shippingAddress = "",
            description = "",
            details = emptyList(),
            price = 0.0,
            status = ""
        )
    )
    val state: StateFlow<OrderDetail> = _state.asStateFlow()
}

@Preview
@Composable
fun OrderInfoScreen(
    orderInfoViewModel: OrderInfoViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {}
) {
    val state by orderInfoViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        orderInfoViewModel.fetchData()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 100.dp)
    ) {
        TopTitleBar(
            name = "Order Info", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        OrderInfo(order = state)
    }
}