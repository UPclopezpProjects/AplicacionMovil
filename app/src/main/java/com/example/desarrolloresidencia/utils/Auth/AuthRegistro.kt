package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.CreationC.User

interface AuthRegistro {
    fun onStarted()
    fun onSuccess(message: Boolean)
    fun onFailure(message: String)
}