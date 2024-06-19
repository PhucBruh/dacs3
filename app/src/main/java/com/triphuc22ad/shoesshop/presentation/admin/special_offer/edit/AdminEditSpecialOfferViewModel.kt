package com.triphuc22ad.shoesshop.presentation.admin.special_offer.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminEditSpecialOfferViewModel @Inject constructor(
    private val specialOfferService: SpecialOfferService,
    private val appStateRepository: AppStateRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val specialOfferId: Int =
        savedStateHandle["specialOfferId"] ?: 0

    private val _state = MutableStateFlow(
        SpecialOffer(
            id = 0,
            description = "",
            active = false,
            productId = 0,
            name = "",
            img = "",
            value = 0.0
        )
    )

    val state: StateFlow<SpecialOffer> = _state.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val response = specialOfferService.getSpecialOfferById(specialOfferId);
            if (response.isSuccessful) {
                val specialOffer = response.body()!!.data

                if (specialOffer != null) {
                    _state.value = specialOffer
                }
            } else {
                appStateRepository.updateNotify("product not found")
            }
        }
    }

    fun changeActive(value: Boolean) {
        _state.value = _state.value.copy(
            active = value
        )
    }

    fun update() {
        viewModelScope.launch {
            val response = _state.value.id?.let {
                specialOfferService.changeActive(
                    id = it,
                    active = _state.value.active
                )
            }
            if (response != null) {
                if (response.isSuccessful) {
                    val result = response.body()!!
                    result.message?.let { appStateRepository.updateNotify(it) }
                } else {
                    appStateRepository.updateNotify("Something error")
                }
            }
        }
    }
}