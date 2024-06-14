package com.triphuc22ad.shoesshop.presentation.admin.product.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val productService: ProductService,
) : ViewModel() {

    fun onEvent(event: AdminProductEvent) {
        when (event) {
            is AdminProductEvent.ChangeQuery -> {
                val state =
                    appStateRepository.appUiState.value.productListUiState
                appStateRepository.updateProductUiState(
                    state.copy(
                        query = event.value,
                    )
                )
            }

            is AdminProductEvent.ChangeQueryProductId -> TODO()
            AdminProductEvent.NextPage -> TODO()
            AdminProductEvent.PreviousPage -> TODO()
            AdminProductEvent.Search -> TODO()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminProductUiState
            val response = productService.getAllProductsForAdmin(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminProductUiState(
                        appStateRepository.appUiState.value.adminProductUiState.copy(
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