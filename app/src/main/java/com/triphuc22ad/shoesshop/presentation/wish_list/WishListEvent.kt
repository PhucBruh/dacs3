package com.triphuc22ad.shoesshop.presentation.wish_list

sealed class WishListEvent {
    data class ChangeBrandFilter(val id: Int) : WishListEvent()
    data class RemoveProduct(val id: Int) : WishListEvent()
    data class SelectProduct(val id: Int) : WishListEvent()
}