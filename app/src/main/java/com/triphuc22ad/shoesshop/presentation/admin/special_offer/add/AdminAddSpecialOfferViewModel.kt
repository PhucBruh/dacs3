package com.triphuc22ad.shoesshop.presentation.admin.special_offer.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminAddSpecialOfferViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(AdminAddSpecialOfferUiState())
    val state: StateFlow<AdminAddSpecialOfferUiState> = _state.asStateFlow()

    fun changeName(value: String) {
        _state.value = _state.value.copy(
            name = value
        )
    }

    fun changeDescription(value: String) {
        _state.value = _state.value.copy(
            description = value
        )
    }

    fun changeProductId(value: Int) {
        _state.value = _state.value.copy(
            productId = value
        )
    }

    fun changeValue(value: Double) {
        _state.value = _state.value.copy(
            value = value
        )
    }
}