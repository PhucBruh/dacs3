package com.triphuc22ad.shoesshop.presentation.wish_list

sealed class WishListAction {
    data class ChangeBrandFilter(val id: Int) : WishListAction()
    data class RemoveProduct(val id: Int) : WishListAction()
    data class SelectProduct(val id: Int) : WishListAction()
}