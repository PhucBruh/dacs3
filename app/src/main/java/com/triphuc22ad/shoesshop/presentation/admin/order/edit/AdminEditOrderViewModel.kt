package com.triphuc22ad.shoesshop.presentation.admin.order.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.OrderService
import com.triphuc22ad.shoesshop.presentation.admin.order.list.AdminOrderEvent
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminEditOrderViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val orderService: OrderService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminEditOrderUiState())
    val state: StateFlow<AdminEditOrderUiState> = _state.asStateFlow()

    private val productId: Int =
        savedStateHandle["orderId"] ?: 0

    fun fetchData() {
        viewModelScope.launch {
            val response = orderService.getOrderById(productId);
            if (response.isSuccessful) {
                val order = response.body()!!.data

                if (order != null) {
                    _state.value = _state.value.copy(
                        orderToEdit = order
                    )
                }
            } else {
                appStateRepository.updateNotify("order not found")
            }
        }
    }

    fun onEvent(event: AdminOrderEvent) {
        when (event) {
            else -> {}
        }
    }

    fun changeStatus(value: String) {
        _state.value = _state.value.copy(
            orderToEdit = _state.value.orderToEdit.copy(
                status = value
            )
        )
    }
}
