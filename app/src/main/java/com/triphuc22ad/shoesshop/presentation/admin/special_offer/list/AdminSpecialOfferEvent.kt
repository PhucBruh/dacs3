package com.triphuc22ad.shoesshop.presentation.admin.special_offer.list

sealed class AdminSpecialOfferEvent {
    data class ChangeQuery(val value: String) : AdminSpecialOfferEvent()
    data class ChangeQueryProductId(val value: Int) : AdminSpecialOfferEvent()
    data object Search : AdminSpecialOfferEvent()
    data class Delete(val id: Int) : AdminSpecialOfferEvent()
    data object NextPage : AdminSpecialOfferEvent()
    data object PreviousPage : AdminSpecialOfferEvent()
}