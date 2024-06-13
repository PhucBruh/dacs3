package com.triphuc22ad.shoesshop.presentation.admin.order.edit

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.admin.order.list.AdminOrderEvent
import com.triphuc22ad.shoesshop.presentation.admin.product.edit.EditProductUiState
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminEditOrderViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminEditOrderUiState())
    val state: StateFlow<AdminEditOrderUiState> = _state.asStateFlow()

    fun onEvent(event: AdminOrderEvent) {
        when (event) {
            else -> {}
        }
    }
}
