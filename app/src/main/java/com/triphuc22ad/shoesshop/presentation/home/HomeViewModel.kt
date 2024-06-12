package com.triphuc22ad.shoesshop.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productService: ProductService,
    private val brandService: BrandService,
    private val specialOfferService: SpecialOfferService,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

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

    fun fetchBrandList() {
        viewModelScope.launch {
            val brandResponse = brandService.getAllBrand();
            if (brandResponse.isSuccessful) {
                val pagedResponse = brandResponse.body()
                val brandList = pagedResponse?.content
                _state.value = _state.value.copy(
                    listBrand = brandList ?: emptyList(),
                )
            }
        }
    }

    fun fetchPopularProduct() {
        viewModelScope.launch {
            val specialOffersResponse = specialOfferService.getAllSpecialOffer(size = 1);
            if (specialOffersResponse.isSuccessful) {
                val pagedResponse = specialOffersResponse.body()
                val specialOffers = pagedResponse?.content
                if (specialOffers != null) {
                    _state.value = _state.value.copy(
                        specialOffer = if (specialOffers.isNotEmpty()) specialOffers[0] else null
                    )
                }
            }
        }
    }

    fun fetchSpecialOffer() {
        viewModelScope.launch {
            val productResponse = productService.getAllProducts();
            if (productResponse.isSuccessful) {
                val pagedResponse = productResponse.body()
                val productList = pagedResponse?.content
                _state.value = _state.value.copy(
                    listPopularProduct = productList ?: emptyList()
                )
            }
        }
    }
}