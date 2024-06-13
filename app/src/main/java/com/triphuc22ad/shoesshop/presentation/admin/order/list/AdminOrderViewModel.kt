package com.triphuc22ad.shoesshop.presentation.admin.order.list

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.admin.inventory.edit.AdminEditInventoryEvent
import com.triphuc22ad.shoesshop.presentation.admin.inventory.edit.AdminEditInventoryUiState
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminOrderViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    fun onEvent(event: AdminOrderEvent) {
        when (event) {
            else -> {}
        }
    }
}
