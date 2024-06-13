package com.triphuc22ad.shoesshop.presentation.admin.brand.edit

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminEditBrandViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AdminEditBrandUiState())
    val state: StateFlow<AdminEditBrandUiState> = _state.asStateFlow()

    fun onEvent(event: AdminEditBrandEvent) {
    }
}