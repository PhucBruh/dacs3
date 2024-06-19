package com.triphuc22ad.shoesshop.presentation.admin.inventory.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminInventoryViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val inventoryService: InventoryService,
) : ViewModel() {

    fun onEvent(event: AdminInventoryEvent) {
        when (event) {
            is AdminInventoryEvent.ChangeQuery -> changeQuery(event.value)
            is AdminInventoryEvent.ChangeQueryProductId -> changeQueryProductId(event.value)
            is AdminInventoryEvent.Delete -> TODO()
            AdminInventoryEvent.Search -> TODO()
        }
    }

    private fun changeQuery(value: String) {
        val state = appStateRepository.appUiState.value.adminInventoryUiState
        appStateRepository.updateAdminInventoryUiState(
            state.copy(searchInfo = value)
        )
    }

    private fun changeQueryProductId(value: Int) {
        val state = appStateRepository.appUiState.value.adminInventoryUiState
        appStateRepository.updateAdminInventoryUiState(
            state.copy(searchId = value)
        )
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminInventoryUiState
            val response = inventoryService.getAllInventory(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminInventoryUiState(
                        state.copy(
                            inventoryList = pagedResponse.content,
                            page = if (pagedResponse.page + 1 > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            val response = inventoryService.delete(id)
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        val state = appStateRepository.appUiState.value.adminInventoryUiState
                        if (state.inventoryList.size == 1) {
                            appStateRepository.updateAdminInventoryUiState(
                                appStateRepository.appUiState.value.adminInventoryUiState.copy(
                                    page = if (state.page - 1 < 0) 0 else state.page - 1,
                                )
                            )
                        }
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                    fetchData()
                } else {
                    appStateRepository.updateNotify("Error")
                }
            }
        }
    }

    fun findById(navigate: (Int) -> Unit) {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminInventoryUiState
            if (state.searchId != 0) {
                val response = inventoryService.check(state.searchId)
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
        val state = appStateRepository.appUiState.value.adminInventoryUiState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateAdminInventoryUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.adminInventoryUiState
        if (state.page > 0) {
            appStateRepository.updateAdminInventoryUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}