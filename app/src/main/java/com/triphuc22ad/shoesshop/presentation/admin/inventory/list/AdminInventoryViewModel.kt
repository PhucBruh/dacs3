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
                            page = pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}