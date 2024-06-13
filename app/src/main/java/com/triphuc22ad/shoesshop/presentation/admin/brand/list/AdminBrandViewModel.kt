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
            else -> {}
        }
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
                            page = if (pagedResponse.page > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}