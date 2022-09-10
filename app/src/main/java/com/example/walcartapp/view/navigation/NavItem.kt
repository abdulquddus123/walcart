package com.example.walcartapp.view.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.walcartapp.R


sealed class NavItem(@StringRes val title: Int, @DrawableRes val icon: Int, val navRoute: String) {
    object Home : NavItem(R.string.home, R.drawable.ic_home, Route.NAV_HOME)
    object Favourite : NavItem(R.string.favourite, R.drawable.ic_favourite, Route.NAV_FAV)
    object Cart : NavItem(R.string.cart, R.drawable.ic_cart, Route.NAV_CART)
    object Account : NavItem(R.string.account, R.drawable.ic_account, Route.NAV_ACCOUNT)
    object Categories : NavItem(R.string.category, R.drawable.ic_category, Route.NAV_CATEGORIES)

}