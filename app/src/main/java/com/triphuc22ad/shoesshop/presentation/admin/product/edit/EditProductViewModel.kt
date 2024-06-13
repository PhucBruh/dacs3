package com.triphuc22ad.shoesshop.presentation.admin.product.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.ColorRequest
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.product_detail.ProductDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val productId: Int =
        savedStateHandle["productId"] ?: 0

    init {
        viewModelScope.launch {
            val response = productService.getProductById(productId);
            if (response.isSuccessful) {
                val product = response.body()!!.data

                if (product != null) {
                    _state.value = _state.value.copy(
                        productDetail = product
                    )
                }
            } else {
                appStateRepository.updateNotify("product not found")
            }
        }
    }

    private val _state = MutableStateFlow(EditProductUiState())
    val state: StateFlow<EditProductUiState> = _state.asStateFlow()

    fun onEvent(event: EditProductEvent) {
        when (event) {
            // add product
            is EditProductEvent.ChangeBrandId -> {
                _state.value = _state.value.copy(
                    productDetail = _state.value.productDetail.copy(
                        brand = _state.value.productDetail.brand.copy(id = event.value)
                    )
                )
            }

            is EditProductEvent.ChangeDescription -> {
                _state.value = _state.value.copy(
                    productDetail = _state.value.productDetail.copy(description = event.value)
                )
            }

            is EditProductEvent.ChangeMainImg -> {
                _state.value = _state.value.copy(
                    productDetail = _state.value.productDetail.copy(mainImg = event.value)
                )
            }

            is EditProductEvent.ChangeName -> {
                _state.value = _state.value.copy(
                    productDetail = _state.value.productDetail.copy(name = event.value)
                )
            }

            is EditProductEvent.ChangePrice -> {
                _state.value = _state.value.copy(
                    productDetail = _state.value.productDetail.copy(price = event.value)
                )
            }

            is EditProductEvent.AddEditSize -> {
                if (event.value != 0) {
                    val sizes = _state.value.editSizes.toMutableList()
                    if (!sizes.contains(event.value)) {
                        sizes.add(event.value)
                        _state.value = _state.value.copy(
                            editSizes = sizes
                        )
                    }
                } else {
                    viewModelScope.launch {
                        appStateRepository.updateNotify("size is null or already added")
                    }
                }
            }

            is EditProductEvent.DeleteEditSize -> {
                val sizes = _state.value.editSizes.toMutableList()
                sizes.remove(event.value)
                _state.value = _state.value.copy(
                    editSizes = sizes
                )
            }

            is EditProductEvent.AddEditColor -> {
                val colors = _state.value.editColors.toMutableList()
                colors.add(ColorRequest(name = event.name, value = event.value))
                _state.value = _state.value.copy(
                    editColors = colors
                )
            }

            is EditProductEvent.DeleteEditColor -> {
                val colors =
                    _state.value.editColors.filter { it.name != event.name && it.value != event.value }
                _state.value = _state.value.copy(
                    editColors = colors
                )
            }

            is EditProductEvent.AddEditImg -> {
                if (event.value.isNotEmpty()) {
                    val imgs = _state.value.editImgs.toMutableList()
                    if (!imgs.contains(event.value)) {
                        imgs.add(event.value)
                        _state.value = _state.value.copy(
                            editImgs = imgs
                        )
                    } else {
                        viewModelScope.launch {
                            appStateRepository.updateNotify("size is null or already added")
                        }
                    }
                }
            }

            is EditProductEvent.DeleteEditImg -> {
                val imgs = _state.value.editImgs.toMutableList()
                imgs.remove(event.value)
                _state.value = _state.value.copy(
                    editImgs = imgs
                )
            }

            EditProductEvent.CheckImg -> {
                _state.value = _state.value.copy(
                    productDetailMainImgPreview = System.currentTimeMillis()
                )
            }

            EditProductEvent.DeleteCheckImg -> {
                _state.value = _state.value.copy(
                    productDetailMainImgPreview = 0L,
                    productDetail = _state.value.productDetail.copy(mainImg = "")
                )
            }

            is EditProductEvent.DeleteColor -> TODO()
            is EditProductEvent.DeleteSize -> TODO()
        }
    }
}