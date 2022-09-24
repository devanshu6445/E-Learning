package com.student.learn.internet.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel(), AuthenticationListener {

    private val repo = AuthenticationRepository(this)
    private lateinit var message: String

    /**
     * -1 initial
     * 100 fail
     * 101 canceled
     * 200 success
     */
    private var code = MutableLiveData(-1)

    fun isLoggedIn(): Boolean = repo.isLoggedIn()

    fun login(
        email: String,
        password: String,
    ) {
        repo.login(email, password)
    }

    override fun loginIsSuccessful() {
        repo.saveDataLocally()
    }

    override fun loginIsFailed(message: String) {
        this.message = message
        code.value = 100
    }

    override fun loginIsCanceled() {
        message = "Login is canceled"
        code.value = 101
    }

    override fun dataSavedSuccessfully() {
        message = "Sign-In successful"
        code.value = 200
    }

    override fun dataSaveFailed() {
        repo.signOut()
        code.value = 100
    }
}