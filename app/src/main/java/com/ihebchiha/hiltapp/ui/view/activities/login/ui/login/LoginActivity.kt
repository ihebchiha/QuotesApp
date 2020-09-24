package com.ihebchiha.hiltapp.ui.view.activities.login.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseUser
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.ui.view.activities.MainActivity
import com.ihebchiha.hiltapp.utils.extensions.CustomDialog
import com.ihebchiha.hiltapp.utils.extensions.EMAIL_INPUT_TYPE
import com.ihebchiha.hiltapp.utils.extensions.checkInputValidity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_register.*


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<TextView>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        forgot_pwd.setOnClickListener(this)

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

        }
        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(username.text.toString(), password.text.toString())
        }
        setupObservers()

        bottomSheetBehavior = BottomSheetBehavior.from(registerBottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        animateArrow(true)
                        register_indication_label.text = getString(R.string.swipe_up_to_register)
                        username.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        animateArrow(false)
                        register_indication_label.text = getString(R.string.swipe_down_to_cancel)
                        username.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        username.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        username.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    else -> {
                    }
                }
            }
        })


    }

    fun animateArrow(isCollapsed: Boolean) {
        var rotation = 360f
        if (!isCollapsed) {
            rotation = 180f
        }
        goToRegisterPageArrow.animate().apply {
            rotation(rotation)
            interpolator = AccelerateInterpolator()
        }.start()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.forgot_pwd -> {
                CustomDialog.showDialogWithField(
                    this,
                    getString(R.string.write_the_email_to_receive_password_reset_request)
                ) { input -> sendPwdResetRequest(input) }
            }
            R.id.login -> {
            }
        }
    }

    private fun sendPwdResetRequest(email: String) {
        if (email.checkInputValidity(EMAIL_INPUT_TYPE)) {
            loginViewModel.sendPasswordResetRequest(email)
        }
    }

    private fun updateUiWithUser(model: FirebaseUser) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            model.uid,
            Toast.LENGTH_LONG
        ).show()

        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        CustomDialog.showPopUpMessageDialog(this, true, getString(errorString), CustomDialog.SLIDE_DOWN_ANIMATION)
    }

    private fun setupObservers() {
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
            if (loginState.noInputEntered != null){
                username.error = getString(loginState.usernameError!!)
                password.error = getString(loginState.passwordError!!)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
        })
    }


}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })


}