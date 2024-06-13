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

    sealed class Admin {
        data object DashBoard : Screen("admin")

        data object Product : Screen("admin/product")
        data object AddProduct : Screen("admin/products/add")

        data object Brand : Screen("admin/brand")
        data object AddBrand : Screen("admin/brand/add")

        data object Inventory : Screen("admin/inventory")
        data object AddInventory : Screen("admin/inventory/add")

        data object Order : Screen("admin/order")

        data object SpecialOffer : Screen("admin/special_offer")
        data object AddSpecialOffer : Screen("admin/special_offer/add")
    }
}