package com.ihebchiha.hiltapp.ui.view.activities.login.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ihebchiha.hiltapp.networking.result.models.PasswordResetRequest
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(username: String, password: String) =
        suspendCoroutine<Result<FirebaseUser>> { itSC ->
            try {
                // TODO: handle loggedInUser authentication
                var user: FirebaseUser? = null
                var error: Result<Nothing>? = null
                val task = firebaseAuth.signInWithEmailAndPassword(username, password)
                task.addOnSuccessListener {
                    user = it.user
                }
                task.addOnCompleteListener {
                    if (it.isSuccessful && it.isComplete) {
                        user = it.result!!.user
                        itSC.resume(Result.Success(user!!))
                    }else{
                        itSC.resume(Result.Error(it.exception!!))
                    }
                }
                task.addOnFailureListener {
                    error = Result.Error(IOException(it))
                }

            } catch (e: Throwable) {
                itSC.resume(Result.Error(IOException("Error logging in", e)))
            }
        }

    suspend fun sendPasswordResetRequest(email: String) =
        suspendCoroutine<Result<PasswordResetRequest>> { itsc ->
            try {
                val passwordResetRequest = PasswordResetRequest(false, null)
                val task = firebaseAuth.sendPasswordResetEmail(email)
                task.addOnFailureListener {
                    itsc.resume(Result.Error(it))
                }
                task.addOnCompleteListener {
                    if (task.isSuccessful) {
                        passwordResetRequest.status = true
                        passwordResetRequest.error = null
                        itsc.resume(Result.Success(passwordResetRequest))
                    }
                }
            } catch (e: Exception) {
                itsc.resume(Result.Error(IOException("Error logging in", e)))
            }
        }

    fun logout() {
        // TODO: revoke authentication
    }
}