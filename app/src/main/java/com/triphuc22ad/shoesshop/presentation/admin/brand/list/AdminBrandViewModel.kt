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
            val response = brandService.getAllBrand(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminBrandUiState(
                        appStateRepository.appUiState.value.adminBrandUIState.copy(
                            brandList = pagedResponse.content,
                            page = pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}