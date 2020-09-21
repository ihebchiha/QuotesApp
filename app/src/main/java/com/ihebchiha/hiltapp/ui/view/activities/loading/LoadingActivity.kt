package com.ihebchiha.hiltapp.ui.view.activities.loading

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.ui.view.activities.MainActivity
import com.ihebchiha.hiltapp.ui.view.activities.login.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_loading.*
import javax.inject.Inject

@AndroidEntryPoint
class LoadingActivity : AppCompatActivity() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // Start the animation (looped playback by default).
        wallpaperContainer.setBackgroundResource(R.drawable.wallpapers_anim)
        val animationDrawable = wallpaperContainer.background as AnimationDrawable
        animationDrawable.start()

        Handler().postDelayed(Runnable { if (firebaseAuth.currentUser == null)
            startActivity(
                Intent(this, LoginActivity::class.java))
        else
            startActivity(
                Intent(this, MainActivity::class.java)) }, 3000)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}