package com.triphuc22ad.shoesshop.presentation.admin.product.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.ColorRequest
import com.triphuc22ad.shoesshop.data.model.ProductRequest
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
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

    private val productId: Int = savedStateHandle["productId"] ?: 0

    fun fetchData() {
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
                    } else {
                        appStateRepository.updateNotify("Size is already added")
                    }
                } else {
                    viewModelScope.launch {
                        appStateRepository.updateNotify("Size is null")
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
                if (event.name.isNotEmpty() && event.value.isNotEmpty()) {
                    val colors = _state.value.editColors.toMutableList()
                    if (colors.find { it.name == event.name && it.value == event.value } == null) {
                        colors.add(ColorRequest(name = event.name, value = event.value))
                        _state.value = _state.value.copy(
                            editColors = colors
                        )
                    } else {
                        appStateRepository.updateNotify("Color is already added")
                    }
                } else {
                    viewModelScope.launch {
                        appStateRepository.updateNotify("Fill the color input")
                    }
                }
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
                        appStateRepository.updateNotify("Img already added")
                    }
                } else {
                    viewModelScope.launch {
                        appStateRepository.updateNotify("Fill the img input")
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

    fun editStatus(value: String) {
        _state.value = _state.value.copy(
            productDetail = _state.value.productDetail.copy(status = value)
        )
    }

    fun update() {
        viewModelScope.launch {
            if (_state.value.productDetail.brand.id != 0) {
                val response = productService.updateProduct(
                    _state.value.productDetail.id, ProductRequest(
                        name = _state.value.productDetail.name,
                        description = _state.value.productDetail.description,
                        price = _state.value.productDetail.price,
                        brandId = _state.value.productDetail.brand.id!!,
                        status = _state.value.productDetail.status,
                        mainImg = _state.value.productDetail.mainImg,
                        sizes = _state.value.editSizes,
                        colors = _state.value.editColors,
                        imgs = _state.value.editImgs,
                    )
                )

                if (response.isSuccessful) {
                    response.body()!!.message?.let { appStateRepository.updateNotify(it) }
                    val product = response.body()!!.data
                    if (product != null) {
                        _state.value = product.let {
                            _state.value.copy(
                                productDetail = it,
                                editImgs = emptyList(),
                                editColors = emptyList(),
                                editSizes = emptyList(),
                            )
                        }
                    }
                } else {
                    appStateRepository.updateNotify("Edit product failed")
                }
            } else {
                appStateRepository.updateNotify("Fill the brand input")
            }
        }
    }

    fun deleteImg(id: Int) {
        viewModelScope.launch {
            val response = productService.deleteImage(
                id = _state.value.productDetail.id, imageId = id
            )
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        _state.value = _state.value.copy(
                            productDetail = _state.value.productDetail.copy(
                                imgs = _state.value.productDetail.imgs.filter { it.id != id }
                            )
                        )
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                }
            } else {
                appStateRepository.updateNotify("Delete image failed")
            }
        }
    }

    fun deleteColor(id: Int) {
        viewModelScope.launch {
            val response = productService.deleteColor(
                id = _state.value.productDetail.id, colorId = id
            )
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        _state.value = _state.value.copy(
                            productDetail = _state.value.productDetail.copy(
                                colors = _state.value.productDetail.colors.filter { it.id != id }
                            )
                        )
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                }
            } else {
                appStateRepository.updateNotify("Delete color failed")
            }
        }
    }

    fun deleteSize(id: Int) {
        viewModelScope.launch {
            val response = productService.deleteSize(
                id = _state.value.productDetail.id, sizeId = id
            )
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        _state.value = _state.value.copy(
                            productDetail = _state.value.productDetail.copy(
                                sizes = _state.value.productDetail.sizes.filter { it.id != id }
                            )
                        )
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                }
            } else {
                appStateRepository.updateNotify("Delete size failed")
            }
        }
    }
}