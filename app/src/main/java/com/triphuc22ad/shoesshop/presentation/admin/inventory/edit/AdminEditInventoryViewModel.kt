package com.triphuc22ad.shoesshop.presentation.admin.inventory.edit

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminEditInventoryViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminEditInventoryUiState())
    val state: StateFlow<AdminEditInventoryUiState> = _state.asStateFlow()

    fun onEvent(event: AdminEditInventoryEvent) {
        when (event) {
            else -> {}
        }
    }
}