package com.ihebchiha.hiltapp.ui.view.activities.login.ui.login

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.ui.view.activities.login.data.LoginRepository
import com.ihebchiha.hiltapp.ui.view.activities.login.data.Result
import kotlinx.coroutines.launch


class LoginViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _forgotPwd = MutableLiveData<Boolean>()
    val forgotPwd = _forgotPwd

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch {
            val result = loginRepository.login(username, password)

            if (result is Result.Success) {
                _loginResult.value =
                    LoginResult(success = result.data)
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun sendPasswordResetRequest(email: String){
        viewModelScope.launch {
            val result = loginRepository.sendPasswordResetRequest(email)

            if (result is Result.Success) {
                _forgotPwd.value = result.data
            } else {
                _forgotPwd.value = false
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}