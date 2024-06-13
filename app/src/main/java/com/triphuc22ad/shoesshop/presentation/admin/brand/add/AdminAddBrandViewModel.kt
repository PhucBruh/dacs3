package com.triphuc22ad.shoesshop.presentation.admin.brand.add

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminAddBrandViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AdminAddBrandUiState())
    val state: StateFlow<AdminAddBrandUiState> = _state.asStateFlow()

    fun onEvent(event: AdminAddBrandEvent) {
    }
}