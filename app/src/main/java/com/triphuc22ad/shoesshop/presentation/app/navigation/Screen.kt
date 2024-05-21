package com.triphuc22ad.shoesshop.presentation.app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Product : Screen("product")
    data object ProductDetail : Screen("product_detail")
    data object Order : Screen("order")
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Profile : Screen("profile")
    data object Cart : Screen("cart")
    data object WishList : Screen("wishlist")
    data object Checkout : Screen("checkout")
    data object SpecialOffer : Screen("special_offer")
}