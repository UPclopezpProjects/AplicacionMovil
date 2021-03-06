package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.CreationC.User

interface AuthRegistro {
    fun onStarted()
    fun onSuccess(message: Boolean, token: String, user: User)
    fun onFailure(message: String)
}