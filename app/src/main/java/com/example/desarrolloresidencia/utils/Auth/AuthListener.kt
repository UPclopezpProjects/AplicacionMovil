package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.Login.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(message: String, token: String, user: User)
    fun onFailure(message: String)
}