package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminEditInventoryViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val inventoryService: InventoryService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminEditInventoryUiState())
    val state: StateFlow<AdminEditInventoryUiState> = _state.asStateFlow()


    private val inventoryId: Int =
        savedStateHandle["inventoryId"] ?: 0

    fun fetchData() {
        viewModelScope.launch {
            val response = inventoryService.getInventoryInfo(inventoryId);
            if (response.isSuccessful) {
                val inventory = response.body()?.data

                if (inventory != null) {
                    _state.value = _state.value.copy(
                        inventoryToEdit = inventory
                    )
                }
            } else {
                appStateRepository.updateNotify("inventory not found")
            }
        }
    }

    fun onEvent(event: AdminEditInventoryEvent) {
        when (event) {
            is AdminEditInventoryEvent.ChangeStock -> {
                _state.value = _state.value.copy(
                    inventoryToEdit = _state.value.inventoryToEdit.copy(
                        stock = event.stock
                    )
                )
            }

            AdminEditInventoryEvent.Update -> TODO()
        }
    }

    fun update() {
        viewModelScope.launch {
            val response = inventoryService.updateStock(
                id = _state.value.inventoryToEdit.id,
                stock = _state.value.inventoryToEdit.stock
            )
            if (response.isSuccessful) {
                response.body()!!.message?.let { appStateRepository.updateNotify(it) }
            } else {
                appStateRepository.updateNotify("Edit inventory failed")
            }
        }
    }
}