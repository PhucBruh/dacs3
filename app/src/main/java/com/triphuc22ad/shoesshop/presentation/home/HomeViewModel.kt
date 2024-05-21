package com.triphuc22ad.shoesshop.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.presentation.home.HomeEvent
import com.triphuc22ad.shoesshop.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val sate = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = sate.asStateFlow()

    init {

    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ClickBrand -> TODO()
            is HomeEvent.ClickProduct -> TODO()
            is HomeEvent.ClickSpecialOffer -> TODO()
            is HomeEvent.SeeAllProduct -> {
                viewModelScope.launch {
                    event.navigateToProduct()
                }
            }

            HomeEvent.SeeAllBrand -> TODO()
            HomeEvent.SeeAllSpecialOffer -> TODO()
        }
    }
}