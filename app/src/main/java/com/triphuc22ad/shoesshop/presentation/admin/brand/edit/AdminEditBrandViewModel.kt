package com.triphuc22ad.shoesshop.presentation.admin.brand.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminEditBrandViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val brandService: BrandService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(AdminEditBrandUiState())
    val state: StateFlow<AdminEditBrandUiState> = _state.asStateFlow()

    private val brandId: Int =
        savedStateHandle["brandId"] ?: 0

    init {
        viewModelScope.launch {
            val response = brandService.getBrandById(brandId);
            if (response.isSuccessful) {
                val brand = response.body()!!.data

                if (brand != null) {
                    _state.value = _state.value.copy(
                        brandToEdit = brand
                    )
                }
            } else {
                appStateRepository.updateNotify("brand not found")
            }
        }
    }

    fun onEvent(event: AdminEditBrandEvent) {
        when (event) {
            is AdminEditBrandEvent.ChangeImage -> {
                _state.value = _state.value.copy(
                    brandToEdit = _state.value.brandToEdit.copy(
                        img_url = event.image
                    )
                )
            }

            is AdminEditBrandEvent.ChangeName -> {
                _state.value = _state.value.copy(
                    brandToEdit = _state.value.brandToEdit.copy(
                        name = event.name
                    )
                )
            }

            AdminEditBrandEvent.CheckImg -> {
                _state.value = _state.value.copy(
                    productDetailMainImgPreview = System.currentTimeMillis()
                )
            }

            AdminEditBrandEvent.DeleteCheckImg -> {
                _state.value = _state.value.copy(
                    productDetailMainImgPreview = 0L,
                    brandToEdit = _state.value.brandToEdit.copy(img_url = ""),
                )
            }
        }
    }
}