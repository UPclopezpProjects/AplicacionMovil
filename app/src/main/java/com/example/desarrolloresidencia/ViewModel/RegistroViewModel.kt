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
                authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                Log.d("si jaló", "${response.body()!!.message}")
            } else{
                //authListener?.onFailure("${response.code()}")
                validacionErr(response.code().toString())
            }
        }

    }

<<<<<<< Updated upstream
    fun validacionErr (error : String){
        if (error == "404"){
            authListener?.onFailure("El email $email ya está en uso")
=======

    fun validarStatus (status : String):String{
        if (status=="false"){
            return "Verifica tu correo electrónico"
>>>>>>> Stashed changes
        }else{
            authListener?.onFailure("Código de error $error")
        }
    }
}