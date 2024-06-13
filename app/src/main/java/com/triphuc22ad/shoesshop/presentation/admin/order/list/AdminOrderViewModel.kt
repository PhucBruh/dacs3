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
            is AdminOrderEvent.ChangeQuery -> TODO()
            is AdminOrderEvent.ChangeQueryProductId -> TODO()
            AdminOrderEvent.Search -> TODO()
        }
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
                            page = if (pagedResponse.page > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}
