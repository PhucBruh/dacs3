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
            val response = if (state.searchInfo.isNotEmpty())
                productService.getAllProductsByQuery(state.searchInfo, state.page, state.size)
            else
                productService.getAllProductsForAdmin(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {

                    appStateRepository.updateAdminProductUiState(
                        appStateRepository.appUiState.value.adminProductUiState.copy(
                            productList = pagedResponse.content,
                            page = if (pagedResponse.page + 1 > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            val response = productService.deleteProduct(id)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    if (apiResponse.success) {
                        val state = appStateRepository.appUiState.value.adminProductUiState
                        if (state.productList.size == 1) {
                            appStateRepository.updateAdminProductUiState(
                                appStateRepository.appUiState.value.adminProductUiState.copy(
                                    page = if (state.page - 1 < 0) 0 else state.page - 1,
                                )
                            )
                        }
                        fetchData()
                    }
                    apiResponse.message?.let { appStateRepository.updateNotify(it) }
                }
            } else {
                appStateRepository.updateNotify("Error")
            }
        }
    }

    fun findById(navigate: (Int) -> Unit) {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminProductUiState
            if (state.searchId != 0) {
                val response = productService.check(state.searchId)
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        if (result.success)
                            navigate(state.searchId)
                    }
                } else {
                    appStateRepository.updateNotify("Inventory not found")
                }
            } else {
                appStateRepository.updateNotify("Error")
            }
        }
    }

    fun nextPage() {
        val state = appStateRepository.appUiState.value.adminProductUiState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateAdminProductUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.adminProductUiState
        if (state.page > 0) {
            appStateRepository.updateAdminProductUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}