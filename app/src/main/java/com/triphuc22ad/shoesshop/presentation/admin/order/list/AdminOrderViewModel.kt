package com.triphuc22ad.shoesshop.presentation.admin.order.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.OrderService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminOrderViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val orderService: OrderService,
) : ViewModel() {

    fun onEvent(event: AdminOrderEvent) {
        when (event) {
            is AdminOrderEvent.ChangeQuery -> changeQuery(event.value)
            is AdminOrderEvent.ChangeQueryProductId -> changeQueryProductId(event.value)
            AdminOrderEvent.Search -> TODO()
        }
    }

    private fun changeQuery(value: String) {
        val state = appStateRepository.appUiState.value.adminOrderUiState
        appStateRepository.updateAdminOrderUiState(
            state.copy(searchInfo = value)
        )
    }

    private fun changeQueryProductId(value: Int) {
        val state = appStateRepository.appUiState.value.adminOrderUiState
        appStateRepository.updateAdminOrderUiState(
            state.copy(searchId = value)
        )
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminProductUiState
            val response = orderService.getAllOrders(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminOrderUiState(
                        appStateRepository.appUiState.value.adminOrderUiState.copy(
                            orderList = pagedResponse.content,
                            page = pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}
