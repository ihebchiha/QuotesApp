package com.ihebchiha.hiltapp.utils.sidemenu

import com.ihebchiha.hiltapp.R


object MenuManager {

    fun getSideMenuItems() : MutableList<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem(R.string.qotd_label, R.drawable.ic_star_24))
        menuItems.add(MenuItem(R.string.all_quotes_label,R.drawable.ic_quote_24))
        menuItems.add(MenuItem(R.string.favorite_label,R.drawable.ic_favorite_24))
        menuItems.add(MenuItem(R.string.profile_label,R.drawable.ic_baseline_account_box_24))
      return menuItems
    }
}