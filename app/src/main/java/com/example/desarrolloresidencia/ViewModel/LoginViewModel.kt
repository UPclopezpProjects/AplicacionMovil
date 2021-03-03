package com.example.desarrolloresidencia.ViewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.AuthListener
import com.example.desarrolloresidencia.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel() : ViewModel()  {

    var email: String ?= null
    var password: String ?= null

    var authListener: AuthListener ?= null

    fun onLoginButtonClick(/*view: View*/){
        authListener?.onStarted()

        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
            } else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }

    }
}