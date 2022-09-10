package com.example.walcartapp.view.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AppBottomNavigation(navController: NavController) {

    val navItems = listOf(
        NavItem.Home,
        NavItem.Categories,
        NavItem.Cart,
        NavItem.Favourite,
        NavItem.Account
    )

    BottomNavigation(
        backgroundColor = Color.White

    ) {

        navItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = stringResource(item.title)) },
                label = { Text(text = stringResource(item.title), fontSize = 9.sp, color = Color(0xffA3A3A3)) },
                selectedContentColor = Color(0xffA3A3A3),


                alwaysShowLabel = true,
                selected = true,
                onClick = {

                }
            )
        }
    }
}