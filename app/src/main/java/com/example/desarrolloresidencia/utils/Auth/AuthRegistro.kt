package com.example.desarrolloresidencia.utils.Auth

import com.example.borradoraplicacin.API.data.model.User

interface AuthRegistro {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}