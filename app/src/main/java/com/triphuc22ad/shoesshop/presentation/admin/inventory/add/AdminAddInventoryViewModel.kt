package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.InventoryRequest
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminAddInventoryViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
    private val inventoryService: InventoryService,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminAddInventoryUiState())
    val state: StateFlow<AdminAddInventoryUiState> = _state.asStateFlow()

    fun onEvent(event: AdminAddInventoryEvent) {
        when (event) {

            is AdminAddInventoryEvent.ChangeProductId -> {
                _state.value = _state.value.copy(
                    productId = event.id
                )
            }

            is AdminAddInventoryEvent.ChangeColor -> {
                _state.value = _state.value.copy(
                    selectedColorId = event.color.id
                )
            }

            is AdminAddInventoryEvent.ChangeSize -> {
                _state.value = _state.value.copy(
                    selectedSizeId = event.size.id
                )
            }

            AdminAddInventoryEvent.CheckProduct -> checkInventoryProduct()
            AdminAddInventoryEvent.Add -> addInventory()
        }
    }

    private fun checkInventoryProduct() {
        viewModelScope.launch {
            val response = productService.getProductById(_state.value.productId);
            if (response.isSuccessful) {
                val product = response.body()!!.data

                if (product != null) {
                    _state.value = _state.value.copy(
                        colorCheckList = product.colors,
                        sizeCheckList = product.sizes
                    )
                    appStateRepository.updateNotify("product founded")
                }
            } else {
                appStateRepository.updateNotify("product not found")
            }
        }
    }

    private fun addInventory() {
        viewModelScope.launch {
            val response = inventoryService.createInventory(
                InventoryRequest(
                    productId = _state.value.productId,
                    colorId = _state.value.selectedColorId,
                    sizeId = _state.value.selectedSizeId,
                )
            )
            if (response.isSuccessful) {
                val apiResponse = response.body()!!
                if (apiResponse.success) {
                    appStateRepository.updateNotify("added")
                    _state.value = AdminAddInventoryUiState()
                } else {
                    apiResponse.message?.let { appStateRepository.updateNotify(it) }
                }
            }
        }
    }
}