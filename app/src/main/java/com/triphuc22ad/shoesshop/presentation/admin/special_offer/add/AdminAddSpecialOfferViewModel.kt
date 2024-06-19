package com.triphuc22ad.shoesshop.presentation.admin.special_offer.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.SpecialOfferRequest
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminAddSpecialOfferViewModel @Inject constructor(
    private val specialOfferService: SpecialOfferService,
    private val appStateRepository: AppStateRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AdminAddSpecialOfferUiState())
    val state: StateFlow<AdminAddSpecialOfferUiState> = _state.asStateFlow()

    fun add() {
        viewModelScope.launch {
            val response = specialOfferService.createSpecialOffer(
                SpecialOfferRequest(
                    name = _state.value.name,
                    description = _state.value.description,
                    productId = _state.value.productId,
                    value = _state.value.value
                )
            )
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        _state.value = AdminAddSpecialOfferUiState()
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                }
            } else if (response.code() == 400) {
                appStateRepository.updateNotify("Pls fill the input")
            }
        }
    }

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