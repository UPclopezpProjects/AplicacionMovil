package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.Login.User

interface AuthRecuperar {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}