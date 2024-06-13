package com.triphuc22ad.shoesshop.presentation.admin.product.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.ColorRequest
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AddProductUiState())
    val state: StateFlow<AddProductUiState> = _state.asStateFlow()

    fun onEvent(event: AddProductEvent) {
        when (event) {
            // add product
            is AddProductEvent.ChangeBrandId -> {
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(brandId = event.value)
                )
            }

            is AddProductEvent.ChangeDescription -> {
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(description = event.value)
                )
            }

            is AddProductEvent.ChangeMainImg -> {
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(mainImg = event.value)
                )
            }

            is AddProductEvent.ChangeName -> {
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(name = event.value)
                )
            }

            is AddProductEvent.ChangePrice -> {
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(price = event.value)
                )
            }

            is AddProductEvent.AddSize -> {
                if (event.value != 0) {
                    val sizes = _state.value.productToAdd.sizes.toMutableList()
                    if (!sizes.contains(event.value)) {
                        sizes.add(event.value)
                        _state.value = _state.value.copy(
                            productToAdd = _state.value.productToAdd.copy(sizes = sizes)
                        )
                    }
                } else {
                    viewModelScope.launch {
                        appStateRepository.updateNotify("size is null or already added")
                    }
                }
            }

            is AddProductEvent.DeleteSize -> {
                val sizes = _state.value.productToAdd.sizes.toMutableList()
                sizes.remove(event.value)
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(sizes = sizes)
                )
            }

            is AddProductEvent.AddColor -> {
                val colors = _state.value.productToAdd.colors.toMutableList()
                colors.add(ColorRequest(name = event.name, value = event.value))
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(colors = colors)
                )
            }

            is AddProductEvent.DeleteColor -> {
                val colors =
                    _state.value.productToAdd.colors.filter { it.name != event.name && it.value != event.value }
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(colors = colors)
                )
            }

            is AddProductEvent.AddImg -> {
                if (event.value.isNotEmpty()) {
                    val imgs = _state.value.productToAdd.imgs.toMutableList()
                    if (!imgs.contains(event.value)) {
                        imgs.add(event.value)
                        _state.value = _state.value.copy(
                            productToAdd = _state.value.productToAdd.copy(imgs = imgs)
                        )
                    } else {
                        viewModelScope.launch {
                            appStateRepository.updateNotify("size is null or already added")
                        }
                    }
                }
            }

            is AddProductEvent.DeleteImg -> {
                val imgs = _state.value.productToAdd.imgs.toMutableList()
                imgs.remove(event.value)
                _state.value = _state.value.copy(
                    productToAdd = _state.value.productToAdd.copy(imgs = imgs)
                )
            }

            AddProductEvent.CheckImg -> {
                _state.value = _state.value.copy(
                    productToAddMainImgPreview = System.currentTimeMillis()
                )
            }

            AddProductEvent.DeleteCheckImg -> {
                _state.value = _state.value.copy(
                    productToAddMainImgPreview = 0L,
                    productToAdd = _state.value.productToAdd.copy(mainImg = "")
                )
            }
        }
    }
}