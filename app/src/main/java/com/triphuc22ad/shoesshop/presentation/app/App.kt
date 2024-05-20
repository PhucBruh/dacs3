package com.triphuc22ad.shoesshop.presentation.app

import android.view.textservice.SpellCheckerInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.triphuc22ad.shoesshop.presentation.app.navigation.Screen
import com.triphuc22ad.shoesshop.presentation.auth.login.LoginScreen
import com.triphuc22ad.shoesshop.presentation.auth.signup.SignupScreen
import com.triphuc22ad.shoesshop.presentation.cart.screen.CheckOutScreen
import com.triphuc22ad.shoesshop.presentation.home.HomeScreen
import com.triphuc22ad.shoesshop.presentation.order.MyOrderScreen
import com.triphuc22ad.shoesshop.presentation.profile.ProfileScreen
import com.triphuc22ad.shoesshop.presentation.special_offers.SpecialOffersScreen
import com.triphuc22ad.shoesshop.presentation.wish_list.WishListScreen

@Composable
fun App(appViewModel: AppViewModel = hiltViewModel()) {
    val state by appViewModel.state.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = if (state.isLoggedIn) {
            Screen.Home.route
        } else {
            Screen.Login.route
        }
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navigateToHome = { navController.navigate(Screen.Home.route) },
                navigateToRegister = { navController.navigate(Screen.SignUp.route) }
            )
        }
        composable(Screen.SignUp.route) {
            SignupScreen(
                navigateToHome = { navController.navigate(Screen.Home.route) },
                navigateToLogin = { navController.navigate(Screen.Login.route) })
        }
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Order.route) { MyOrderScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Cart.route) { ProfileScreen() }
        composable(Screen.WishList.route) { WishListScreen() }
        composable(Screen.Checkout.route) { CheckOutScreen() }
        composable(Screen.SpecialOffer.route) { SpecialOffersScreen() }
    }
}