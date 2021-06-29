package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.responseUser
import kotlinx.coroutines.launch

class ActualizarViewModel(): ViewModel() {
    var nombre: String? = null
    var apellidoP: String? = null
    var apellidoM: String? = null
    //var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun actualizar() {
        viewModelScope.launch {
            authListener?.onStarted()
            try {
                //Coroutines.main {
                val response = AmazonRepository().actualizarUsuario("$nombre", "$apellidoP", "$apellidoM", "$password", "${responseUser.user?.email}", "${responseUser.token}")

                if (response.isSuccessful) {
                    responseUser.message = response.body()!!.message
                    responseUser.token = response.body()!!.token
                    responseUser.user = response.body()!!.user

                    authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                } else {
                    authListener?.onFailure("${response.errorBody()?.string()}")
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("No se pudo conectar con el servidor")
            }
        }
    }
}