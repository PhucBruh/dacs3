package com.triphuc22ad.shoesshop.presentation.admin.special_offer.list

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.presentation.admin.order.list.AdminOrderEvent
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminSpecialOfferViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {

    fun onEvent(event: AdminSpecialOfferEvent) {
        when (event) {
            else -> {}
        }
    }
}
