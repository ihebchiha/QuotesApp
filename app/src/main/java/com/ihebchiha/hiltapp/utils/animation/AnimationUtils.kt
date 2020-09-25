package com.ihebchiha.hiltapp.utils.animation

import android.view.View
import android.view.animation.AccelerateInterpolator
import kotlinx.android.synthetic.main.layout_register.*

object AnimationUtils {

    fun animateWithRotation(view : View, isCollapsed: Boolean) {
        var rotation = 360f
        if (!isCollapsed) {
            rotation = 180f
        }
        view.animate().apply {
            rotation(rotation)
            interpolator = AccelerateInterpolator()
        }.start()
    }
}