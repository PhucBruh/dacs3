package com.triphuc22ad.shoesshop.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
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
                        query = event.query,
                    )
                )
            }

            is ProductEvent.ClickProduct -> TODO()
            ProductEvent.LoadMore -> TODO()
            ProductEvent.Search -> TODO()
            ProductEvent.ResetSearch -> TODO()
        }
    }

    fun applyFilter(filter: ProductListFilter) {
        val state =
            appStateRepository.appUiState.value.productListUiState
        appStateRepository.updateProductUiState(
            state.copy(
                filter = filter,
                applyFilter = true,
                page = 0
            )
        )
        fetchData()
        appStateRepository.updateNotify("Apply filter")
    }

    fun resetFilter() {
        val state =
            appStateRepository.appUiState.value.productListUiState
        appStateRepository.updateProductUiState(
            state.copy(
                filter = ProductListFilter(),
                page = 0,
                applyFilter = false
            )
        )
        fetchData()
        appStateRepository.updateNotify("Reset filter")
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.productListUiState
            val productResponse: Response<PagedResponse<Product>>;
            if (state.applyFilter || state.query.isNotEmpty()) {
                productResponse = productService.search(
                    query = state.query,
                    isFilter = state.applyFilter,
                    orderBy = state.filter.orderBy,
                    sortBy = state.filter.sortBy,
                    saleStatus = state.filter.saleStatus,
                    minPrice = state.filter.minPrice,
                    maxPrice = state.filter.maxPrice,
                    page = state.page,
                    size = state.size
                );
            } else {
                productResponse = productService.getAllProducts(state.page, state.size);
            }
            if (productResponse.isSuccessful) {
                val pagedResponse = productResponse.body()
                if (pagedResponse != null) {
                    appStateRepository.updateProductUiState(
                        appStateRepository.appUiState.value.productListUiState.copy(
                            productList = pagedResponse.content,
                            page = if (pagedResponse.page + 1 > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }

    fun nextPage() {
        val state = appStateRepository.appUiState.value.productListUiState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateProductUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.productListUiState
        if (state.page > 0) {
            appStateRepository.updateProductUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}