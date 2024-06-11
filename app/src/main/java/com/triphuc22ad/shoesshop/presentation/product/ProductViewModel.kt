package com.triphuc22ad.shoesshop.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.ProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productService: ProductService,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())
    val state: StateFlow<ProductUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val productResponse = productService.getAllProducts();
            if (productResponse.isSuccessful) {
                val pagedResponse = productResponse.body()
                val productList = pagedResponse?.content
                _state.value = _state.value.copy(
                    productList = productList ?: emptyList(),
                    totalPage = pagedResponse?.totalPages ?: 0
                )
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.ChangeQuery -> TODO()
            is ProductEvent.ClickProduct -> TODO()
            ProductEvent.LoadMore -> TODO()
            ProductEvent.Search -> TODO()
            ProductEvent.ResetSearch -> TODO()
        }
    }
}