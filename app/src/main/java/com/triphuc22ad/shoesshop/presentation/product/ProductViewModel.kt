package com.triphuc22ad.shoesshop.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
) : ViewModel() {

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.ChangeQuery -> {
                val state =
                    appStateRepository.appUiState.value.productListUiState
                appStateRepository.updateProductUiState(
                    state.copy(
                        filter = state.filter.copy(query = event.query)
                    )
                )
            }

            is ProductEvent.ClickProduct -> TODO()
            ProductEvent.LoadMore -> TODO()
            ProductEvent.Search -> TODO()
            ProductEvent.ResetSearch -> TODO()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminProductUiState
            val productResponse = productService.getAllProducts(state.page, state.size);
            if (productResponse.isSuccessful) {
                val pagedResponse = productResponse.body()
                if (pagedResponse != null) {
                    appStateRepository.updateProductUiState(
                        appStateRepository.appUiState.value.productListUiState.copy(
                            productList = pagedResponse.content,
                            page = if (pagedResponse.page > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}