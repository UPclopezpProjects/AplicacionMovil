package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.Login.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(message: Boolean, token: String, user: User)
    fun onFailure(message: String)
}