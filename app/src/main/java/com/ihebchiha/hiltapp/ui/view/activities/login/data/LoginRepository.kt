package com.ihebchiha.hiltapp.ui.view.activities.login.data

import com.google.firebase.auth.FirebaseUser
import com.ihebchiha.hiltapp.networking.result.models.PasswordResetRequest

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository constructor(private val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: FirebaseUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<FirebaseUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    suspend fun sendPasswordResetRequest(email: String): Result<PasswordResetRequest> {
        // handle Pwd Reset Request
        return dataSource.sendPasswordResetRequest(email)
    }

    private fun setLoggedInUser(loggedInUser: FirebaseUser) {
        this.user = loggedInUser

    }
}