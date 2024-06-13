package com.triphuc22ad.shoesshop.presentation.admin.inventory.add

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminAddInventoryViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminAddInventoryUiState())
    val state: StateFlow<AdminAddInventoryUiState> = _state.asStateFlow()

    fun onEvent(event: AdminAddInventoryEvent) {
        when (event) {
            else -> {}
        }
    }
}