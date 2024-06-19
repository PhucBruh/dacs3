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
            val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
            val response = if (state.searchInfo.isNotEmpty())
                specialOfferService.getAllSpecialOfferByQuery(
                    state.searchInfo,
                    state.page,
                    state.size
                )
            else
                specialOfferService.getAllSpecialOffer(state.page, state.size)
            if (response.isSuccessful) {
                val pagedResponse = response.body()
                if (pagedResponse != null) {
                    appStateRepository.updateAdminSpecialOfferUiState(
                        appStateRepository.appUiState.value.adminSpecialOfferUiState.copy(
                            specialOfferList = pagedResponse.content,
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
            val response = specialOfferService.delete(id)
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    if (result.success) {
                        val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
                        if (state.specialOfferList.size == 1) {
                            appStateRepository.updateAdminSpecialOfferUiState(
                                appStateRepository.appUiState.value.adminSpecialOfferUiState.copy(
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
            val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
            if (state.searchId != 0) {
                val response = specialOfferService.check(state.searchId)
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
        val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
        if (state.page + 1 < state.totalPage) {
            appStateRepository.updateAdminSpecialOfferUiState(
                state.copy(page = state.page + 1)
            )
            fetchData()
        }
    }

    fun previousPage() {
        val state = appStateRepository.appUiState.value.adminSpecialOfferUiState
        if (state.page > 0) {
            appStateRepository.updateAdminSpecialOfferUiState(
                state.copy(page = state.page - 1)
            )
            fetchData()
        }
    }
}
