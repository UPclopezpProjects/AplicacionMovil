package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Coroutines

class LoginViewModel() : ViewModel()  {

    var email: String ?= null
    var password: String ?= null

    var authListener: AuthListener?= null

    fun onLoginButtonClick(){
        authListener?.onStarted()

        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                validarU(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                //authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
            } else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }

    }

    fun validarU(message: Boolean, token: String, user: User){
        if(user.typeOfUser=="consumer"){
            authListener?.onSuccess(message, token, user)
        }else{
            authListener?.onFailure("Solo pueden ingresar los usuarios de tipo consumidor")
        }
    }
}