package com.ihebchiha.hiltapp.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ihebchiha.hiltapp.Event
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.utils.constants.FADE_OUT_ANIMATION
import com.ihebchiha.hiltapp.utils.constants.SLIDE_DOWN_ANIMATION

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
                EspressoIdlingResource.increment()
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                EspressoIdlingResource.decrement()
            }
        })
        show()
    }
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {

    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), timeLength)
        }
    })
}

fun AppCompatActivity.navigateToFragment(fragment: Fragment, @IdRes container: Int){
    this.supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}

fun AppCompatActivity.navigateWithPayload(activity: AppCompatActivity, payload: Bundle){
    startActivity(Intent(this.applicationContext, activity::class.java), payload)
}

fun Activity.showPopUpMessageDialog(
    context: Activity,
    @IdRes whereToShow: Int,
    isError: Boolean,
    message: String,
    animationType: Int
) {
    val viewGroup = context.findViewById<ViewGroup>(whereToShow)
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


