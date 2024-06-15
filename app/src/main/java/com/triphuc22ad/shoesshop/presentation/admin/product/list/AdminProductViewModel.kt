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
            is AdminProductEvent.ChangeQuery -> changeQuery(event.value)
            is AdminProductEvent.ChangeQueryProductId -> changeQueryProductId(event.value)
            is AdminProductEvent.Delete -> TODO()
            AdminProductEvent.NextPage -> TODO()
            AdminProductEvent.PreviousPage -> TODO()
            AdminProductEvent.Search -> TODO()
        }
    }

    private fun changeQuery(value: String) {
        val state = appStateRepository.appUiState.value.adminProductUiState
        appStateRepository.updateAdminProductUiState(
            state.copy(searchInfo = value)
        )
    }

    private fun changeQueryProductId(value: Int) {
        val state = appStateRepository.appUiState.value.adminProductUiState
        appStateRepository.updateAdminProductUiState(
            state.copy(searchId = value)
        )
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
                            page = pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}