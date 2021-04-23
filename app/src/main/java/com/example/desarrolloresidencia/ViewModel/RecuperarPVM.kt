package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Auth.AuthRecuperar
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.responseUser

class RecuperarPVM() : ViewModel(){
    var email: String ?= null
    var authListener: AuthRecuperar?= null

    fun recuperar(){
        authListener?.onStarted()

        Coroutines.main {
            val response = UserRepository().recuperarPassword("$email", )

            if (response.isSuccessful){
                authListener?.onSuccess(response.body()!!.message)
            } else{
                authListener?.onFailure("${response.errorBody()?.string()}")
            }
        }
    }
}