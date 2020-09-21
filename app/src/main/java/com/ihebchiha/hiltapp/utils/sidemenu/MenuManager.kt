package com.ihebchiha.hiltapp.utils.sidemenu

import com.ihebchiha.hiltapp.R


object MenuManager {

    fun getSideMenuItems() : MutableList<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem(R.string.qotd_label, R.drawable.ic_star))
        menuItems.add(MenuItem(R.string.all_quotes_label,R.drawable.ic_quote_24))
        menuItems.add(MenuItem(R.string.favorite_label,R.drawable.ic_favorite))
        menuItems.add(MenuItem(R.string.profile_label,R.drawable.ic_account_box))
        menuItems.add(MenuItem(R.string.logout, R.drawable.ic_logout))
      return menuItems
    }
}