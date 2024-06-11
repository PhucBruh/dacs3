package com.triphuc22ad.shoesshop.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {

    private val _state = MutableStateFlow(OrderUiState())
    val state: StateFlow<OrderUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val inCompletedOrdersResponse = userService.getMyOrder(completed = false);
            if (inCompletedOrdersResponse.isSuccessful) {
                val inCompletedOrders = inCompletedOrdersResponse.body()?.data
                _state.value = _state.value.copy(
                    inCompletedOrders = inCompletedOrders ?: emptyList()
                )
            }

            val completedOrderResponse = userService.getMyOrder(completed = true);
            if (completedOrderResponse.isSuccessful) {
                val completedOrders = completedOrderResponse.body()?.data
                _state.value = _state.value.copy(
                    completedOrders = completedOrders ?: emptyList()
                )
            }
        }
    }

    fun onEvent(event: OrderEvent) {

    }

}