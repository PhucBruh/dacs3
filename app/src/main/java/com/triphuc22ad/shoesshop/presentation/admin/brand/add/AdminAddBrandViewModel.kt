package com.triphuc22ad.shoesshop.presentation.admin.brand.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.BrandRequest
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminAddBrandViewModel @Inject constructor(
    private val brandService: BrandService,
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AdminAddBrandUiState())
    val state: StateFlow<AdminAddBrandUiState> = _state.asStateFlow()

    fun onEvent(event: AdminAddBrandEvent) {
        when (event) {
            is AdminAddBrandEvent.ChangeImage -> {
                _state.value = _state.value.copy(
                    img = event.image
                )
            }

            is AdminAddBrandEvent.ChangeName -> {
                _state.value = _state.value.copy(
                    name = event.name
                )
            }

            AdminAddBrandEvent.CheckImg -> {
                _state.value = _state.value.copy(
                    brandToAddMainImgPreview = System.currentTimeMillis()
                )
            }

            AdminAddBrandEvent.DeleteCheckImg -> {
                _state.value = _state.value.copy(
                    brandToAddMainImgPreview = 0L,
                    img = ""
                )
            }
        }
    }

    fun add() {
        viewModelScope.launch {
            val response = brandService.create(
                BrandRequest(
                    name = _state.value.name,
                    imgUrl = _state.value.img,
                )
            )
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        _state.value = AdminAddBrandUiState()
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                }
            } else if (response.code() == 400) {
                appStateRepository.updateNotify("Pls fill the input")
            }
        }
    }
}