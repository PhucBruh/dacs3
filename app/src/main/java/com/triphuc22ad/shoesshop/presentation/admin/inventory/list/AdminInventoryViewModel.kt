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

        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminInventoryUiState
            val response = inventoryService.getAllInventory(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminInventoryUiState(
                        appStateRepository.appUiState.value.adminInventoryUiState.copy(
                            inventoryList = pagedResponse.content,
                            page = if (pagedResponse.page > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}