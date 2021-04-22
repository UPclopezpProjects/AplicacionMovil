package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.responseUser

class ActualizarViewModel(): ViewModel() {
    var nombre: String ?= null
    var apellidoP: String ?= null
    var apellidoM: String ?= null
    var email: String ?= null
    var password: String ?= null

    var authListener: AuthListener?= null

    fun actualizar(){
        authListener?.onStarted()

        Coroutines.main {
            val response = UserRepository().actualizarUsuario("$nombre", "$apellidoP", "$apellidoM", "$email", "$password", "${responseUser.user?.email}", "${responseUser.token}")

            if (response.isSuccessful){
                responseUser.message = response.body()!!.message
                responseUser.token = response.body()!!.token
                responseUser.user = response.body()!!.user

                authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
            } else{
                authListener?.onFailure("${response.errorBody()?.string()}")
            }
        }
    }
}