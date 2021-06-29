package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthRecuperar
import kotlinx.coroutines.launch

class RecuperarPVM() : ViewModel(){
    var email: String ?= null
    var authListener: AuthRecuperar?= null

    fun recuperar(){
        viewModelScope.launch {
            authListener?.onStarted()
            try {
                //CoroutinesRecCont.main {
                val response = AmazonRepository().recuperarPassword("$email", )

                if (response.isSuccessful) {
                    authListener?.onSuccess(response.body()!!.message)
                } else {
                    authListener?.onFailure("${response.errorBody()?.string()}")
                }
            }catch (e : java.net.SocketTimeoutException){
                authListener?.onFailure("No se pudo conectar con el servidor")
            }
        }
    }
}