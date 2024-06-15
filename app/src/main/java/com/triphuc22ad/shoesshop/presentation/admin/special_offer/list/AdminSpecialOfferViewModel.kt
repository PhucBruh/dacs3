package com.triphuc22ad.shoesshop.presentation.admin.special_offer.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminSpecialOfferViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val specialOfferService: SpecialOfferService
) : ViewModel() {

    fun onEvent(event: AdminSpecialOfferEvent) {
        when (event) {
            is AdminSpecialOfferEvent.ChangeQuery -> changeQuery(event.value)
            is AdminSpecialOfferEvent.ChangeQueryProductId -> changeQueryProductId(event.value)
            is AdminSpecialOfferEvent.Delete -> TODO()
            AdminSpecialOfferEvent.NextPage -> TODO()
            AdminSpecialOfferEvent.PreviousPage -> TODO()
            AdminSpecialOfferEvent.Search -> TODO()
        }
    }

    private fun changeQuery(value: String) {
        val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
        appStateRepository.updateAdminSpecialOfferUiState(
            state.copy(searchInfo = value)
        )
    }

    private fun changeQueryProductId(value: Int) {
        val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
        appStateRepository.updateAdminSpecialOfferUiState(
            state.copy(searchId = value)
        )
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = appStateRepository.appUiState.value.adminProductUiState
            val response = specialOfferService.getAllSpecialOfferByAdmin(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminSpecialOfferUiState(
                        appStateRepository.appUiState.value.adminSpecialOfferUiState.copy(
                            specialOfferList = pagedResponse.content,
                            page = pagedResponse.page,
                            totalPage = pagedResponse.totalPages,
                        )
                    )
                }
            }
        }
    }
}
