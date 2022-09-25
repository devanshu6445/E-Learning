package com.student.learn.internet.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel(), AuthenticationListener {

    private val repo = AuthenticationRepository(this)
    lateinit var message: String

    /**
     * -1 initial
     * 100 fail
     * 101 canceled
     * 200 success
     */
    private val code1 = MutableLiveData(-1)
    val code:LiveData<Int> = code1

    fun init(){
        code1.value = -1
    }

    fun isLoggedIn(): Boolean = repo.isLoggedIn()

    fun login(
        email: String,
        password: String,
    ) {
        repo.login(email, password)
    }

    fun sendPasswordResetLink(email: String){
        repo.sendEmailToRequestForResetPassword(email)
    }

    fun isEmailValid(email: String) =
         Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun loginIsSuccessful() {
        repo.saveDataLocally()
    }

    override fun loginIsFailed(message: String) {
        this.message = message
        code1.value = 100
    }

    override fun loginIsCanceled() {
        message = "Login is canceled"
        code1.value = 101
    }

    override fun resetEmailSentSuccessfully() {
        message = "Please check you email"
        code1.value = 200
    }

    override fun resetEmailSentFailed(message: String) {
        this.message = message
        code1.value = 100
    }

    override fun dataSavedSuccessfully() {
        message = "Sign-In successful"
        code1.value = 200
    }

    override fun dataSaveFailed() {
        repo.signOut()
        code1.value = 100
    }
}