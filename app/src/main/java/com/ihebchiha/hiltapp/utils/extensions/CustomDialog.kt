package com.ihebchiha.hiltapp.utils.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.ihebchiha.hiltapp.R

object CustomDialog {
    const val SLIDE_DOWN_ANIMATION = 1
    const val FADE_OUT_ANIMATION = 2

    fun showPopUpMessageDialog(
        context: FragmentActivity,
        isError: Boolean,
        message: String,
        animationType: Int
    ) {
        val viewGroup = context.findViewById<ViewGroup>(android.R.id.content)
        val view = LayoutInflater.from(context).inflate(R.layout.error_layout, viewGroup, false);
        view.findViewById<TextView>(R.id.messageTV).text = message
        view.findViewById<ConstraintLayout>(R.id.messageContainer).background =
            if (isError) ContextCompat.getDrawable(
                context,
                R.drawable.view_error_message_rounded_corners
            ) else ContextCompat.getDrawable(context, R.drawable.view_message_rounded_corners)
        viewGroup.addView(view)
        AnimationUtils.loadAnimation(context, R.anim.animation).also { animation ->
            view.startAnimation(animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {
                    p0!!.cancel()
                    view.animate().apply {
                        when (animationType) {
                            SLIDE_DOWN_ANIMATION -> {
                                translationY(250f)
                            }
                            FADE_OUT_ANIMATION -> {
                                alpha(0f)
                            }
                            else -> {
                                alpha(0f)
                            }
                        }
                        startDelay = 1500
                        duration = 1500
                        interpolator = AccelerateInterpolator()
                    }.start()
                }
            })
        }
    }
}