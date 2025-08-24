package com.uncannyvalley.mentora.presentation.ui.login

import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.MutableLiveData

class LoginViewModel() : ViewModel() {

    // LiveData that holds whether the login form is valid
    val loginFormState = MutableLiveData<Boolean>()

    fun loginDataChanged(email: String, password: String) {
        loginFormState.value = isEmailValid(email) && isPasswordValid(password)
    }

    fun isEmailValid(email: String): Boolean {
        return if (email.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 3
    }
}