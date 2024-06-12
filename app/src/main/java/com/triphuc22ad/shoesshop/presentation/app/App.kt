package com.triphuc22ad.shoesshop.presentation.app

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.triphuc22ad.shoesshop.presentation.app.navigation.BottomNavItem
import com.triphuc22ad.shoesshop.presentation.app.navigation.BottomNavigationBar
import com.triphuc22ad.shoesshop.presentation.app.navigation.Screen
import com.triphuc22ad.shoesshop.presentation.auth.login.LoginScreen
import com.triphuc22ad.shoesshop.presentation.auth.signup.SignupScreen
import com.triphuc22ad.shoesshop.presentation.cart.screen.CartScreen
import com.triphuc22ad.shoesshop.presentation.cart.screen.CheckOutScreen
import com.triphuc22ad.shoesshop.presentation.home.HomeScreen
import com.triphuc22ad.shoesshop.presentation.order.MyOrderScreen
import com.triphuc22ad.shoesshop.presentation.product.ProductScreen
import com.triphuc22ad.shoesshop.presentation.product_detail.ProductDetailScreen
import com.triphuc22ad.shoesshop.presentation.product_detail.ProductDetailViewModel
import com.triphuc22ad.shoesshop.presentation.profile.ProfileScreen
import com.triphuc22ad.shoesshop.presentation.special_offers.SpecialOffersScreen
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import kotlinx.coroutines.launch
import okhttp3.internal.notify

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(appViewModel: AppViewModel = hiltViewModel()) {
    val state by appViewModel.state.collectAsState()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state.notify.id) {
        if (state.notify.value.isNotEmpty()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(state.notify.value)
            }
        }
    }

    val navItems = listOf(
        BottomNavItem(title = "Home", icon = Icons.Outlined.Home, route = Screen.Home.route),
        BottomNavItem(
            title = "Product", icon = Icons.Outlined.Store, route = Screen.Product.route
        ),
        BottomNavItem(
            title = "Cart", icon = Icons.Outlined.ShoppingBag, route = Screen.Cart.route
        ),
        BottomNavItem(
            title = "Order", icon = Icons.Outlined.ShoppingCart, route = Screen.Order.route
        ),
        BottomNavItem(
            title = "Profile", icon = Icons.Outlined.Person, route = Screen.Profile.route
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screen.Home.route, Screen.Product.route, Screen.Cart.route, Screen.Order.route, Screen.Profile.route -> {
            appViewModel.showBottomBar(true)
        }

        else -> {
            appViewModel.showBottomBar(false)
        }
    }

    AppTheme(darkTheme = state.isDarkMode) {

        Box(contentAlignment = Alignment.BottomCenter) {
            NavHost(
                navController = navController, startDestination = if (state.isLoggedIn) {
                    Screen.Home.route
                } else {
                    Screen.Login.route
                }, modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = if (state.showBottomBar) 96.dp else 0.dp,
                        top = if (navBackStackEntry?.destination?.route == Screen.Product.route) 0.dp else 32.dp,
                    )
            ) {
                composable(Screen.Login.route) {
                    LoginScreen(navigateToHome = { navController.navigate(Screen.Home.route) },
                        navigateToRegister = { navController.navigate(Screen.SignUp.route) })
                }
                composable(Screen.SignUp.route) {
                    SignupScreen(navigateToLogin = { navController.navigate(Screen.Login.route) })
                }

                composable(Screen.Home.route) {
                    BackHandler(true) {
                        // Or do nothing
                    }
                    HomeScreen(
                        navigateToSpecialOffer = { navController.navigate(Screen.SpecialOffer.route) },
                        navigateToProduct = {
                            navController.navigate(Screen.Product.route) {
                                popUpTo(Screen.Product.route) { inclusive = true }
                            }
                        },
                        navigateToProductDetail = { navController.navigate(Screen.Product.route + "/$it") })
                }
                composable(Screen.Product.route) {
                    BackHandler(true) {
                        // Or do nothing
                    }

                    ProductScreen(
                        navigateToProductDetail = { navController.navigate(Screen.Product.route + "/$it") }
                    )
                }

                composable(
                    Screen.Product.route + "/{productId}",
                    arguments = listOf(navArgument("productId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val productId =
                        backStackEntry.arguments?.getInt("productId") ?: return@composable
                    ProductDetailScreen(
                        onBackClick = { navController.popBackStack() })
                }

                composable(Screen.Cart.route) {

                    BackHandler(true) {
                        // Or do nothing
                    }
                    CartScreen(navigateToCheckout = { navController.navigate(Screen.Checkout.route) })
                }

                composable(Screen.Order.route) {
                    BackHandler(true) {
                        // Or do nothing
                    }
                    MyOrderScreen()
                }

                composable(Screen.Profile.route) {
                    BackHandler(true) {
                        // Or do nothing
                    }

                    ProfileScreen()
                }

                composable(Screen.Checkout.route) {
                    BackHandler(true) {
                        // Or do nothing
                    }

                    CheckOutScreen(navigateBack = { navController.popBackStack() })
                }

                composable(Screen.SpecialOffer.route) {
                    SpecialOffersScreen(
                        navigateBack = { navController.popBackStack() },
                        navigateToProductDetail = { navController.navigate(Screen.Product.route + "/$it") })
                }
            }

            if (state.showBottomBar) BottomNavigationBar(
                navItems = navItems,
                navController = navController
            )

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 100.dp)
            )
        }
    }
}