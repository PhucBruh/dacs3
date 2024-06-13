package com.triphuc22ad.shoesshop.presentation.admin.brand.list

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
}