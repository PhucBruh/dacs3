package com.triphuc22ad.shoesshop.presentation.home

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.home.HomeEvent
import com.triphuc22ad.shoesshop.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val sate = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = sate.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ClickBrand -> TODO()
            is HomeEvent.ClickProduct -> TODO()
            is HomeEvent.ClickSpecialOffer -> TODO()
            HomeEvent.SeeAllBrand -> TODO()
            HomeEvent.SeeAllProduct -> TODO()
            HomeEvent.SeeAllSpecialOffer -> TODO()
        }
    }
}