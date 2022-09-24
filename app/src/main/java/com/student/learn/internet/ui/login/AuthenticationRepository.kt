package com.student.learn.internet.ui.login

import com.google.firebase.auth.FirebaseAuth
import com.student.learn.internet.ui.login.data.ProfileDetails

internal class AuthenticationRepository(private val listener: AuthenticationListener) {

    private val auth = FirebaseAuth.getInstance()

    fun login(
        email: String,
        password: String
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            listener.loginIsSuccessful()

        }.addOnFailureListener {
            it.message?.let { it1 -> listener.loginIsFailed(it1) }

        }.addOnCanceledListener {
            listener.loginIsCanceled()
        }
    }

    fun saveDataLocally() {
        listener.dataSavedSuccessfully()
    }

    fun isEmailVerified(): Boolean {
        return false
    }

    fun loginWithEmailLink() {

    }

    fun sendEmailToRequestForResetPassword(email: String) {

    }

    fun createAccount(profileDetails: ProfileDetails) {

    }

    fun isLoggedIn() = auth.currentUser != null

    fun signOut() = auth.signOut()

}

internal interface AuthenticationListener {
    fun loginIsSuccessful()
    fun loginIsFailed(message: String)
    fun loginIsCanceled()
    fun dataSavedSuccessfully()
    fun dataSaveFailed()
}