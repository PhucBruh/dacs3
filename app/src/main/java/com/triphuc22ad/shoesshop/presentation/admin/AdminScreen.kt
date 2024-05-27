package com.triphuc22ad.shoesshop.presentation.admin


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.triphuc22ad.shoesshop.presentation.admin.components.DashboardScreen
import com.triphuc22ad.shoesshop.presentation.admin.components.OrderScreen
import com.triphuc22ad.shoesshop.presentation.admin.components.ProductsScreen
import com.triphuc22ad.shoesshop.presentation.admin.components.ProfileScreen
import com.triphuc22ad.shoesshop.presentation.admin.components.editOrder
import com.triphuc22ad.shoesshop.presentation.admin.components.orders
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

sealed class AdminNavItem(val title: String, val icon: ImageVector, val path: String) {
    data object Dashboard : AdminNavItem("Dashboard", Icons.Default.Home, "dashboard")
    data object Products : AdminNavItem("Products", Icons.Default.List, "products")
    data object Orders : AdminNavItem("Orders", Icons.Default.ShoppingCart, "orders")
    data object Profile : AdminNavItem("Profile", Icons.Default.Person, "profile")
}

@Composable
fun AdminBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        AdminNavItem.Dashboard,
        AdminNavItem.Products,
        AdminNavItem.Orders,
        AdminNavItem.Profile
    )
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            AdminBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        AdminNavigationScreens(navController = navController, paddingValues = innerPadding)
    }
}

@Composable
fun AdminNavigationScreens(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController,
        startDestination = AdminNavItem.Dashboard.path,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(AdminNavItem.Dashboard.path) { DashboardScreen() }
        composable(AdminNavItem.Products.path) { ProductsScreen() }
        composable(AdminNavItem.Orders.path) { OrderScreen(orders = orders, onEditOrder = ::editOrder) }
        composable(AdminNavItem.Profile.path) { ProfileScreen() }
    }
}

@Preview
@Composable
fun AdminScreenPreview() {
    AppTheme {
        Surface {
            AdminScreen()
        }
    }
}