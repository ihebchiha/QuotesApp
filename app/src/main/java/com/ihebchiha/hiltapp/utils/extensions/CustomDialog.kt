package com.ihebchiha.hiltapp.utils.extensions

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.ihebchiha.hiltapp.R

object CustomDialog {
    const val SLIDE_DOWN_ANIMATION = 1
    const val FADE_OUT_ANIMATION = 2

    fun showPopUpMessageDialog(
        context: AppCompatActivity,
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

    fun showDialogWithField(activity: AppCompatActivity, titleToPut: String, action: (email: String) -> Unit){
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_with_field)
        val title = dialog.findViewById(R.id.titleTV) as TextView
        title.text = titleToPut
        val sendButton = dialog.findViewById(R.id.sendButton) as Button
        val emailET = dialog.findViewById<EditText>(R.id.emailET)
        sendButton.setOnClickListener { action(emailET.text.toString()) }
        dialog.show()
    }
}