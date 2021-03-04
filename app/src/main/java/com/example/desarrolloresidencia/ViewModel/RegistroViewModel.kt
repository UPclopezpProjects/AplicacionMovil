package com.example.desarrolloresidencia.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import com.example.desarrolloresidencia.utils.Coroutines

class RegistroViewModel(): ViewModel() {
    var nombre: String ?= null
    var apellidoP: String ?= null
    var apellidoM: String ?= null
    var email: String ?= null
    var password: String ?= null

    var authListener: AuthRegistro ?= null

    fun onLoginButtonClick(){
        authListener?.onStarted()

        Coroutines.main {
            val response = UserRepository().userRegistro(nombre!!, apellidoP!!, apellidoM!!, email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()!!.message)
            } else{
                Log.d("viewmodel", response.errorBody().toString())

                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }

    }
}