package com.example.desarrolloresidencia.ViewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.UI.ScannerQR
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.responseUser
import java.lang.Exception

class LoginViewModel() : ViewModel()  {

    var email: String ?= null
    var password: String ?= null

    var authListener: AuthListener?= null

    fun onLoginButtonClick(){
        try {


        authListener?.onStarted()

        Coroutines.main {
            //val response = UserRepository().userLogin(email!!, password!!)
            val response = AmazonRepository().userLogin(email!!, password!!)

            if (response.isSuccessful){
                responseUser.message = response.body()!!.message
                responseUser.token = response.body()!!.token
                responseUser.user = response.body()!!.user

                Log.d("login token", "${responseUser.token}")
                validarU(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                //authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
            } else{
                authListener?.onFailure("${response.errorBody()?.string()}")
            }
        }
        } catch (e: Exception){
            Log.e("Error ViewModel", "$e")
        }
    }

    fun validarU(message: Boolean, token: String, user: User){
        if(user.typeOfUser=="Consumer"){
            authListener?.onSuccess(message, token, user)
        }else{
            authListener?.onFailure("Solo pueden ingresar los usuarios de tipo consumidor")
        }
    }

}