package com.triphuc22ad.shoesshop.presentation.admin.brand.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminBrandViewModel @Inject constructor(
    private val brandService: BrandService,
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    fun onEvent(event: AdminBrandEvent) {
        when (event) {
            is AdminBrandEvent.ChangeQuery -> changeQuery(event.value)
            is AdminBrandEvent.ChangeQueryProductId -> changeQueryProductId(event.value)
            is AdminBrandEvent.Delete -> TODO()
            AdminBrandEvent.Search -> TODO()
        }
    }

    private fun changeQuery(value: String) {
        val state = appStateRepository.appUiState.value.adminBrandUIState
        appStateRepository.updateAdminBrandUiState(
            state.copy(searchInfo = value)
        )
    }

    private fun changeQueryProductId(value: Int) {
        val state = appStateRepository.appUiState.value.adminBrandUIState
        appStateRepository.updateAdminBrandUiState(
            state.copy(searchId = value)
        )
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminBrandUIState
            val response = if (state.searchInfo.isNotEmpty())
                brandService.getAllBrandByQuery(state.searchInfo, state.page, state.size)
            else
                brandService.getAllBrand(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminBrandUiState(
                        appStateRepository.appUiState.value.adminBrandUIState.copy(
                            brandList = pagedResponse.content,
                            page = if (pagedResponse.page + 1 > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            val response = brandService.delete(id)
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        val state = appStateRepository.appUiState.value.adminBrandUIState
                        if (state.brandList.size == 1) {
                            appStateRepository.updateAdminBrandUiState(
                                appStateRepository.appUiState.value.adminBrandUIState.copy(
                                    page = if (state.page - 1 < 0) 0 else state.page - 1,
                                )
                            )
                        }
                    }
                    result.message?.let { appStateRepository.updateNotify(it) }
                    fetchData()
                } else {
                    appStateRepository.updateNotify("Error")
                }
            }
        }
    }

    fun findById(navigate: (Int) -> Unit) {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminBrandUIState
            if (state.searchId != 0) {
                val response = brandService.check(state.searchId)
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
        val state = appStateRepository.appUiState.value.adminBrandUIState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateAdminBrandUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.adminBrandUIState
        if (state.page > 0) {
            appStateRepository.updateAdminBrandUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}