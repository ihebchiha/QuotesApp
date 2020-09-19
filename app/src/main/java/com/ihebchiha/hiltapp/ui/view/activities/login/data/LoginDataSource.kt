package com.ihebchiha.hiltapp.ui.view.activities.login.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ihebchiha.hiltapp.ui.view.activities.login.data.model.LoggedInUser
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource constructor(private val firebaseAuth: FirebaseAuth){

    suspend fun login(username: String, password: String)= suspendCoroutine<Result<FirebaseUser>> { itSC ->
        try {
            // TODO: handle loggedInUser authentication
            var user: FirebaseUser? = null
            var error: Result<Nothing>? = null
                val task =   firebaseAuth.signInWithEmailAndPassword(username, password)
                task.addOnSuccessListener {
                    user = it.user
                }
                task.addOnCompleteListener {
                    if(it.isSuccessful && it.isComplete) {
                        user = it.result!!.user
                        itSC.resume(Result.Success(user!!))
                    }
                }
                task.addOnFailureListener {
                    error =  Result.Error(IOException(it))
                }

        } catch (e: Throwable) {
            itSC.resume(Result.Error(IOException("Error logging in", e)))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}