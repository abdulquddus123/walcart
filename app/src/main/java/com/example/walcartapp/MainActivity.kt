package com.example.walcartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.walcartapp.ui.theme.WalcartAppTheme
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walcartapp.ui.theme.WalcartAppTheme
import com.example.walcartapp.view.navigation.AppBottomNavigation
import com.example.walcartapp.view.navigation.Route.NAV_ACCOUNT
import com.example.walcartapp.view.navigation.Route.NAV_CART
import com.example.walcartapp.view.navigation.Route.NAV_CATEGORIES
import com.example.walcartapp.view.navigation.Route.NAV_FAV
import com.example.walcartapp.view.navigation.Route.NAV_HOME
import com.example.walcartapp.view.screen.ExpandableCard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalcartAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    MainScreen()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) },
        drawerContent = { Text(text = "Drawer") }
    ) {
        NavHost(navController = navController, startDestination = NAV_CATEGORIES) {
            composable(NAV_HOME) { AppScreen(text = "Home Screen") }
            composable(NAV_FAV) { AppScreen(text = "Favourite Screen") }
            composable(NAV_CATEGORIES) { ExpandableCard() }
            composable(NAV_CART) { AppScreen(text = "Cart Screen") }
            composable(NAV_ACCOUNT) { AppScreen(text = "Account Screen") }
        }
    }
}
@Composable
fun AppScreen(text: String) = Surface(modifier = Modifier.fillMaxSize()) {
    Text(text = text, fontSize = 32.sp)
}

