package com.triphuc22ad.shoesshop.presentation

sealed class Screen(
    val route: String
) {
    data object Home : Screen("home")
    data object Cart : Screen("cart")
    data object Order : Screen("order")
    data object Product : Screen("product")
    data object CheckOut : Screen("check_out")
    data object Login : Screen("login")
    data object Signup : Screen("signup")
    data object Profile : Screen("profile")
    data object SpecialOffers: Screen("special_offers")
    data object WishList: Screen("wish_list")
}