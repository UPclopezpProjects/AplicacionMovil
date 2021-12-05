package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.GetLog.Message
import com.example.desarrolloresidencia.Network.model.Login.User

interface AuthGetLog {
    fun onStarted()
    fun onSuccess(message:Message)
    fun onFailure(message: String)
}