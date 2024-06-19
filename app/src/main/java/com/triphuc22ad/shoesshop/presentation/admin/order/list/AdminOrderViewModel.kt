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
            val state = appStateRepository.appUiState.value.adminOrderUiState
            val response = if (state.searchInfo.isNotEmpty())
                orderService.getAllOrdersByQuery(state.searchInfo, state.page, state.size)
            else orderService.getAllOrders(state.page, state.size)
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

    fun findById(navigate: (Int) -> Unit) {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminOrderUiState
            if (state.searchId != 0) {
                val response = orderService.check(state.searchId)
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        if (result.success)
                            navigate(state.searchId)
                    }
                } else {
                    appStateRepository.updateNotify("Inventory not found")
                }
            } else {
                appStateRepository.updateNotify("Error")
            }
        }
    }

    fun nextPage() {
        val state = appStateRepository.appUiState.value.adminOrderUiState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateAdminOrderUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.adminOrderUiState
        if (state.page > 0) {
            appStateRepository.updateAdminOrderUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}
