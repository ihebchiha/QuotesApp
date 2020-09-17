package com.ihebchiha.hiltapp.utils.sidemenu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)